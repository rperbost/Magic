package server;

import ihm.MainFrame;
import ihm.card.CardDrawable;
import ihm.screen.*;

import java.util.ArrayList;

import binder.Booster;
import binder.Card;
import binder.Deck;
import binder.SingletonBinder;

import player.IA;
import player.Human;
import player.Player;

public class Main {
	public static void main(String args[]){

		CoreApply apply = CoreApply.getInstance();
		
		
		
		//A MODIFIER : code de test
		ArrayList<Player>playersTest = new ArrayList<Player>();
		for(int i = 0; i < 8;i++){
			playersTest.add(new IA());
		}
		Player p0 = new Human("José");
		playersTest.set(0, p0);
		
		MainFrame mainFrame = MainFrame.getInstance();
		//mainFrame.addScreen("test", new TestScreen() ).activeScreen("test");
		
		
		mainFrame.addScreen("DRAFT", new DraftScreen(p0) ).activeScreen("DRAFT");
		
		String [] setsTest = {"13m","13m","13m"};
		ServerThread st = new ServerThread(playersTest,setsTest);
		st.start();
		
		//mainFrame.addScreen("DECK", new DeckScreen("13m",new Deck())).activeScreen("DECK");

		//mainFrame.addScreen("IA",new IaImprovementScreen()).activeScreen("IA");
		
	}	
}
