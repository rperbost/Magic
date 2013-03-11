package server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;

import binder.BoosterFactory;
import binder.ImplementedDeck;
import binder.interfaces.Booster;
import binder.interfaces.Card;

import player.*;

public class DraftThread extends Thread{
	//timers : 1 1 55 55 50 45 45 40 4O 35 30 25 20 10 10
	public static final int[] TIMERS = {
			60000,60000,55000,55000,50000,
			45000,45000,40000,40000,35000,
			30000,25000,20000,10000,10000,
	};
	private LinkedList< Booster > boosterStock; 
	
	int idCreateur;
	private ArrayList< Player > players;
	
	int timer;
	public DraftThread (int idCreateur,ArrayList<Player> players, String[] sets) throws RemoteException{
		this.players = players;
		this.idCreateur = idCreateur;
		
		for(int i = players.size();i < 8;i++){
			players.add(new IA("BOT"+i));
		}
		
		for(int i = 0;i < 8;i++){
			Player p = this.players.get(i);
			p.setChair(i);
			p.setPick(new ImplementedDeck());
		}
		
		this.boosterStock = new LinkedList<Booster>();
		for(int i = 0;i < 3;i++){
			for(int j = 0;j < 8;j++){
				this.boosterStock.push(BoosterFactory.getBooster(Server.getInstance().getBinder().getSet(sets[i])));
			}
		}
	}
	
	private boolean areTheyAllReady() {
		for(Player p : players){
			if(!p.isReady())return false;
		}
		return true;
	}


	public String getSerializedData(int id) throws RemoteException {
		String r = "";
		for(int i = 0; i < 8; i++){
			if(i!=0)r+="&";
			r += this.players.get(i).getName();
		}
		r+="\t";
		for(int i = 0; i < 8; i++){
			if(i!=0)r+="&";
			r += this.players.get(i).isReady();
		}
		r+="\t";
		
		Player target = null;
		for(Player p:players){
			if(p.getId() == id)target = p;
		}
		
		r+=target.getSelectedCardId();
		r+="\t";
		for(int i=0;i<target.getPick().getMainDeck().size();i++){
			if(i!=0)r+="&";
			r+=target.getPick().getMainDeck().get(i);
		}
		r+="\t";
		for(int i=0;i<target.getCurrentBooster().size();i++){
			if(i!=0)r+="&";
			r+=target.getCurrentBooster().get(i);
		}
		r+="\t";
		r+=this.timer;
		
		return r;
	}

	public void refreshPlayers(){
		for(int i = 0; i < players.size();i++){
			try {
				players.get(i).refresh();
			} catch (RemoteException e) {
				Server.clients.remove(players.get(i));
			}
		}
	}

	private void rotate(int nbBooster) throws RemoteException{
		Booster firstBooster = players.get(0).getCurrentBooster();
		if(firstBooster.size()>0){
			for(int i = 0;i < 7; i++){
				Player p = players.get(i);
				Player pNext = players.get(i+1);
				p.setCurrentBooster(pNext.getCurrentBooster());
			}
			players.get(7).setCurrentBooster(firstBooster);
		}
	}

	public void run() {
		for(int i = 0; i < players.size();i++){
			try {
				players.get(i).startDraftScreen();
			} catch (RemoteException e) {
				Server.clients.remove(players.get(i));
				try {
					players.add(i,new IA(players.get(i)));
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		}
		for(int nbBooster = 0;nbBooster < 3;nbBooster++){
			for(Player p : players){
				try {
					p.setCurrentBooster(boosterStock.pollLast());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			for(int nbCarte = 0;nbCarte < 15;nbCarte++){
				System.out.println("draft"+idCreateur+" carte"+nbCarte+" booster "+nbBooster+" timer "+TIMERS[nbCarte]);
				this.timer = TIMERS[nbCarte];
				
				refreshPlayers();
				
				while(this.timer>0 && !areTheyAllReady()){
					try {
						Thread.sleep(100);
						this.timer-=100;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for(Player p : players){
					try {
						String selectedRef = p.getSelectedCard().getReference();
						Card selectedCard = Server.getInstance().getBinder().getByRef(selectedRef);
						p.getCurrentBooster().remove(selectedCard);
						p.getPick().addToDeck(selectedCard);
					} catch (RemoteException e) {
						e.printStackTrace();
					}	
				}
				try {
					this.rotate(nbBooster);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
		
		for(int i = 0; i < players.size();i++){
			try {
				players.get(i).startDeckListScreen(players.get(i).getPick());
			} catch (RemoteException e) {
				Server.clients.remove(players.get(i));
			}
		}
	}
	
	public void selectCard(int id, Card card) throws RemoteException {
		for(Player p : players){
			if(p.getId() == id)p.setSelectedCard(card);
		}
		refreshPlayers();
	}
}
