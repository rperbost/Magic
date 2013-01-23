package server;

import java.util.ArrayList;

import binder.BoosterFactory;
import binder.Card;
import binder.Booster;

import player.Player;

public class ServerThread extends Thread{
	private ArrayList<Player> players;
	
	private ArrayList< ArrayList<Card> > picks;
	private ArrayList< ArrayList< Booster > >boosters;
	
	private boolean[] readyFlags;
	
	int timer;
	
	//timers : 1 1 55 55 50 45 45 40 4O 35 30 25 20 10 10
	public static final int[] TIMERS = {
			60000,60000,55000,55000,50000,
			45000,45000,40000,40000,35000,
			30000,25000,20000,10000,10000,
	};
	public ServerThread (ArrayList<Player> players, String[] sets){
		this.players = players;
		this.picks = new ArrayList<ArrayList<Card>>();
		this.boosters = new ArrayList<ArrayList< Booster >>();
		this.readyFlags = new boolean[8];
		BoosterFactory boosterFactory = BoosterFactory.getInstance();
		
		for(int i = 0;i < 8;i++){
			this.players.get(i).setChair(i);
			this.players.get(i).setCallback(this);
			this.picks.add(new ArrayList<Card>());
			this.boosters.add(new ArrayList<Booster>());
			for(int j = 0;j < 3;j++){
				this.boosters.get(i).add(boosterFactory.getBooster(sets[j]));
			}
		}
	}
	
	
	public void notifyReady(Player me){
		this.readyFlags[me.getChair()] = true;
		refresh();
	}
	
	private void refresh() {
		for(int i = 0;i < 8;i++){
			this.players.get(i).refresh();			
		}
	}


	public void run() {
		for(int nbBooster = 0;nbBooster < 3;nbBooster++){
			for(int nbCarte = 0;nbCarte < 15;nbCarte++){
				System.out.println("carte"+nbCarte+" booster "+nbBooster+" timer "+TIMERS[nbCarte]);
	
				for(int nbPlayers = 0;nbPlayers < 8;nbPlayers++){
					this.readyFlags[nbPlayers] = false;
					players.get(nbPlayers).setBooster(boosters.get(nbPlayers).get(nbBooster));
				}
				this.timer = TIMERS[nbCarte];
				while(this.timer>0 && !areTheyAllReady()){
					try {
						Thread.sleep(100);
						this.timer-=100;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for(int nbJoueurs = 0;nbJoueurs < 8;nbJoueurs++){
					Card selectedCard = players.get(nbJoueurs).getSelectedCard();
					boosters.get(nbJoueurs).get(nbBooster).remove(selectedCard);
					picks.get(nbJoueurs).add(selectedCard);
				}
				this.rotate(nbBooster);
			}
		}
	}


	private boolean areTheyAllReady() {
		for(int i = 0;i < 8;i++){
			if(this.readyFlags[i] == false)return false;
		}
		return true;
		//return false;
	}

	private void rotate(int nbBooster){
		Booster firstBooster = this.boosters.get(0).get(nbBooster);
		for(int i = 0;i < 7; i++){
			this.boosters.get(i).set(nbBooster,this.boosters.get(i+1).get(nbBooster));
		}
		this.boosters.get(7).set(nbBooster,firstBooster);
	}

	public int getTimer() {
		return this.timer;
	}
	
	public String[] getPseudos() {
		String[] readys = new String[8];
		for(int i = 0; i < 8; i++){
			readys[i] = this.players.get(i).getPseudo();
		}
		return readys;
	}
	public boolean[] getReadys() {
		return readyFlags;
	}


	public Booster getDeck(Player player) {
		return new Booster(this.picks.get(player.getChair()));
	}
}
