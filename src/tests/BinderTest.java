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
	public void testCardBinderAdd(){
		testAdd(new CardBinder());
	}
	
	@Test
	public void testSetBinderAdd(){
		testAdd(new SetBinder());
	}
	
	public void testGet(Binder binder){
		Card c1 = new Card("c1","refzdzd","creature",Card.Rarity.rare);
		Card c2 = new Card("a_c2","refvvvv","creature",Card.Rarity.common);
		Card c3 = new Card("c1","refplop","creature",Card.Rarity.uncommon);
		binder.addCard(c1);
		binder.addCard(c2);
		binder.addCard(c3);
		
		System.out.println(binder.getClass().getName());
		for(int i = 0;i <3; i++){
			assertFalse(binder.get(i) == null);
			System.out.println("\t"+binder.get(i));
			
		}
	}
	
	@Test
	public void testCardBinderGet() {
		testGet(new CardBinder());
	}
	
	@Test
	public void testSetBinderGet(){
		testGet(new SetBinder());
	}
	
	
	
}
