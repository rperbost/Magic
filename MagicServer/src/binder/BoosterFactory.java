package binder;

import java.rmi.RemoteException;
import java.util.ArrayList;

import binder.Booster;
import binder.ICard.Rarity;

public class BoosterFactory {
		
	public static IBooster getBooster(IBinder binder) throws RemoteException{
		ArrayList<ICard> booster = new ArrayList<ICard>(15);
		ArrayList<Rarity> rarities = new ArrayList<Rarity>();
		
		for(int i = 0; i < binder.size() ; i++){
			Rarity rarity = binder.get(i).getRarity();
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
				ICard card = binder.get((int)(binder.size()*Math.random()));
				
				if(!booster.contains(card) && card.getRarity() == rarityExpected[i]){
					booster.add(i, card);
					break;
				}
			}
			
			if(nbTry>=1000)return null;
		}
		
		return new Booster(booster);
	}
	
	public BoosterFactory(){
	}
}
