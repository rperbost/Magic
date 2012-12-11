package server;

import ihm.DraftScreen;
import ihm.MainFrame;

import java.util.ArrayList;

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
		
		DraftScreen dScreen = new DraftScreen();
		dScreen.setPlayer(p0);
		mainFrame.addScreen("DRAFT", dScreen ).activeScreen("DRAFT");
		
		String [] setsTest = {"13m","6th","mrd"};
		ServerThread st = new ServerThread(playersTest,setsTest);
		st.start();
	
	}	
}
