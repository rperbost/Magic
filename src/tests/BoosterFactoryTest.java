package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import binder.Booster;
import binder.BoosterFactory;
import binder.Card;
import binder.SingletonBinder;


public class BoosterFactoryTest {
	;
	SingletonBinder masterBinder = SingletonBinder.getInstance();
	BoosterFactory boosterFactory = BoosterFactory.getInstance();
	
	@Test
	public void testAnyBoosterContainFifteenCards(){
		ArrayList<String> editions = masterBinder.getSets();
		for(int i=0;i< editions.size();i++){
			assertTrue(boosterFactory.getBooster(editions.get(i)).size() == 15);
		}
	}
	
	@Test
	public void testBoosterAreWellFormed(){
		Booster aBooster = boosterFactory.getBooster("13m");
		for(int i=0;i< 50;i++){
			assertTrue(aBooster.get(0).getRarity() == Card.Rarity.rare||aBooster.get(0).getRarity() == Card.Rarity.mythic);
			for(int k=1;k< 4;k++){
				assertTrue(aBooster.get(k).getRarity() == Card.Rarity.uncommon);	
			}
			for(int k=4;k< 15;k++){
				assertTrue(aBooster.get(k).getRarity() == Card.Rarity.common);	
			}
		}
	}
}
