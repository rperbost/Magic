package server;

import java.util.ArrayList;

import binder.BoosterFactory;
import binder.SingletonBinder;

public class Main {
	public static void main(String args[]){
		CoreApply apply = CoreApply.getInstance();
		SingletonBinder masterBinder = SingletonBinder.getInstance();
		BoosterFactory boosterFactory = BoosterFactory.getInstance();
		
		ArrayList<String> editions = masterBinder.getSets();
		for(int i=0;i< editions.size();i++){
			System.out.println(i+" "+editions.get(i)+" "+boosterFactory.getBooster(editions.get(i)));
		}
	}	
}
