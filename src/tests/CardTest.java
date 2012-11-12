package tests;

import static org.junit.Assert.*;
import leecher.PageLeecher;

import org.junit.Test;

import binder.Card;


public class CardTest {

	@Test
	public void testEquals(){
		Card c1 = new Card("CarteTest","ref1","creature",Card.Rarity.common);
		Card c2 = new Card("CarteTest","ref1","creature",Card.Rarity.common);
		Card c3 = new Card("CarteNonTest","ref1","creature",Card.Rarity.common);
		Card c4 = new Card("CarteTest","ref2","creature",Card.Rarity.common);
		
		assertTrue(c1.equals(c2));
		assertFalse(c1.equals(c3));
		assertFalse(c2.equals(c3));
		assertFalse(c1.equals(c4));
	}
	
	@Test
	public void testRarity(){
		PageLeecher pl = new PageLeecher("http://www.magic-ville.com/fr/carte.php?ref=tsp358");
		assertTrue(Card.GetRarity(pl.getPageContent()) == Card.Rarity.timeshifted);
		pl = new PageLeecher("http://www.magic-ville.com/fr/carte.php?ref=alp221");
		assertTrue(Card.GetRarity(pl.getPageContent()) == Card.Rarity.common);
		pl = new PageLeecher("http://www.magic-ville.com/fr/carte.php?ref=mrd028");
		assertTrue(Card.GetRarity(pl.getPageContent()) == Card.Rarity.uncommon);
		pl = new PageLeecher("http://www.magic-ville.com/fr/carte.php?ref=urs177");
		assertTrue(Card.GetRarity(pl.getPageContent()) == Card.Rarity.rare);
		pl = new PageLeecher("http://www.magic-ville.com/fr/carte.php?ref=roe004");
		assertTrue(Card.GetRarity(pl.getPageContent()) == Card.Rarity.mythic);
		pl = new PageLeecher("http://www.magic-ville.com/fr/carte.php?ref=rtr285");
		assertTrue(Card.GetRarity(pl.getPageContent()) == Card.Rarity.unknow);
	}
}
