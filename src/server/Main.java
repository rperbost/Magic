package server;

import ihm.DeckScreen;
import ihm.DraftScreen;
import ihm.MainFrame;

import java.util.ArrayList;

import binder.Booster;
import binder.Deck;

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
		
		DraftScreen draftScreen = new DraftScreen(p0);
		mainFrame.addScreen("DRAFT", draftScreen ).activeScreen("DRAFT");
		
		String [] setsTest = {"13m","13m","13m"};
		//ServerThread st = new ServerThread(playersTest,setsTest);
		//st.start();
		
		DeckScreen deckScreen = new DeckScreen("13m",new Deck());
		mainFrame.addScreen("DECK", deckScreen).activeScreen("DECK");
	}	
}
