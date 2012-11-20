package binder;

import java.util.ArrayList;

import binder.Card.Rarity;

public class BoosterFactory {
	static private BoosterFactory theInstance = null;
	
	private SingletonBinder masterBinder;
	
	public static BoosterFactory getInstance(){
		if(theInstance==null){
			theInstance = new BoosterFactory();
		}
		return theInstance;
	}
	
	private BoosterFactory(){
		masterBinder = SingletonBinder.getInstance();
	}
	
	public ArrayList<Card> getBooster(String set){
		ArrayList<Card> booster = new ArrayList<Card>(15);
		ArrayList<Rarity> rarities = new ArrayList<Rarity>();
		
		for(int i = 0; i < masterBinder.size(set) ; i++){
			Rarity rarity = masterBinder.get(set,i).rarity;
			if(!rarities.contains(rarity))rarities.add(rarity);
		}
		
		Rarity[] rarityExpected = new Rarity[15];
		
		rarityExpected[0] = Rarity.rare;
		
		for(int i = 0;i < 15;i++){
			rarityExpected[i] = Rarity.unknow;
		}
		if(rarities.contains(Rarity.common)){
			for(int i = 0;i < 15;i++){
				rarityExpected[i] = Rarity.common;
			}
		}
		if(rarities.contains(Rarity.uncommon)){
			for(int i = 0;i < 4;i++){
				rarityExpected[i] = Rarity.uncommon;
			}
		}
		if(rarities.contains(Rarity.timeshifted)){
			rarityExpected[14] = Rarity.timeshifted;
		}
		if(rarities.contains(Rarity.rare)){
			rarityExpected[0] = Rarity.rare;
		}
		if(rarities.contains(Rarity.mythic)){
			rarityExpected[0] = Rarity.mythic;
		}
		
		
		for(int i = 0;i < 15;i++){
			int nbTry = 0;
			
			while(nbTry<10000){
				nbTry++;
				Card card = masterBinder.get(set,(int)(masterBinder.size(set)*Math.random()));
				
				if(!booster.contains(card) && card.rarity == rarityExpected[i]){
					booster.add(i, card);
					break;
				}
			}
			
			if(nbTry>=1000)return null;
		}
		
		return booster;
	}
}
