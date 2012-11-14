package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import binder.*;


public class BinderTest {

	public boolean testAdd(Binder binder){
		assertTrue(binder.size()==0);
		
		Card c1 = new Card("c1","ref1","creature",Card.Rarity.common);
		Card c2 = new Card("a_c2","ref1","creature",Card.Rarity.common);
		Card c3 = new Card("c1","ref2","creature",Card.Rarity.common);
		
		binder.addCard(c1);
		assertTrue(binder.size()==1);
		binder.addCard(c2);
		assertTrue(binder.size()==2);
		binder.addCard(c1);
		assertTrue(binder.size()==2);
		binder.addCard(c3);
		assertTrue(binder.size()==3);
		
		return true;
	}
	@Test
	public void testCardBinder(){
		assertTrue(testAdd(new CardBinder()));
	}
	
	@Test
	public void testRarityBinder(){
		testAdd(new RarityBinder());
	}
	@Test
	public void testSetBinder(){
		testAdd(new SetBinder());
	}
	
}
