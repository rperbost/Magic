package server;

import java.util.ArrayList;

import player.IA;
import player.Player;

import binder.BoosterFactory;
import binder.SingletonBinder;

public class Main {
	public static void main(String args[]){
		CoreApply apply = CoreApply.getInstance();
		SingletonBinder masterBinder = SingletonBinder.getInstance();
		BoosterFactory boosterFactory = BoosterFactory.getInstance();
		
		//A MODIFIER : code de test
		ArrayList<Player>playersTest = new ArrayList<Player>();
		for(int i = 0; i < 8;i++){
			playersTest.add(new IA());
		}
		String [] setsTest = {"13m","6th","mrd"};
		ServerThread st = new ServerThread(playersTest,setsTest);
		st.start();
	}	
}
