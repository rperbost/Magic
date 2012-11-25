package server;

import java.util.ArrayList;

import player.IA;
import player.Human;
import player.Player;

public class Main {
	public static void main(String args[]){
		@SuppressWarnings("unused")
		CoreApply apply = CoreApply.getInstance();
		
		//A MODIFIER : code de test
		ArrayList<Player>playersTest = new ArrayList<Player>();
		for(int i = 0; i < 8;i++){
			playersTest.add(new IA());
		}
		playersTest.set(0, new Human());
		String [] setsTest = {"13m","6th","mrd"};
		ServerThread st = new ServerThread(playersTest,setsTest);
		st.start();
	}	
}
