package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import binder.Card;
import binder.CardBinder;


public class CardBinderTest {

	@Test
	public void testAddCard(){
		CardBinder cb = new CardBinder();
		assertTrue(cb.size()==0);
		
		Card c1 = new Card("c1","ref1","creature",Card.Rarity.common);
		Card c2 = new Card("a_c2","ref1","creature",Card.Rarity.common);
		Card c3 = new Card("c1","ref2","creature",Card.Rarity.common);
		
		cb.addCard(c1);
		assertTrue(cb.size()==1);
		cb.addCard(c2);
		assertTrue(cb.size()==2);
		cb.addCard(c1);
		assertTrue(cb.size()==2);
		cb.addCard(c3);
		assertTrue(cb.size()==3);
	}
	
}
