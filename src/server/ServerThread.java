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
		
		BoosterFactory boosterFactory = BoosterFactory.getInstance();
		
		for(int i = 0;i < 8;i++){
			this.picks.add(new ArrayList<Card>());
			this.boosters.add(new ArrayList<Booster>());
			for(int j = 0;j < 3;j++){
				this.boosters.get(i).add(boosterFactory.getBooster(sets[j]));
			}
		}
	}
	
	
	public void run() {
		for(int nbBooster = 0;nbBooster < 3;nbBooster++){
			for(int nbCarte = 0;nbCarte < 15;nbCarte++){
				System.out.println("carte"+nbCarte+" booster "+nbBooster+" timer "+TIMERS[nbCarte]);

				
				for(int nbJoueurs = 0;nbJoueurs < 8;nbJoueurs++){
					players.get(nbJoueurs).setBooster(boosters.get(nbJoueurs).get(nbBooster));
				}
				
				try {
					Thread.sleep(TIMERS[nbCarte]/1000);//A MODIFIER : retirer le /1000
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				for(int nbJoueurs = 0;nbJoueurs < 8;nbJoueurs++){
					Card selectedCard = players.get(nbJoueurs).getSelectedCard();
					boosters.get(nbJoueurs).get(nbBooster).remove(selectedCard);
					picks.get(nbJoueurs).add(selectedCard);
				}
			}
		}
	}
}
