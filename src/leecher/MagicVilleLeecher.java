package leecher;

import java.util.Vector;

import binder.Card;

public class MagicVilleLeecher {

	public static Vector<String> getEditions() {
		PageLeecher pl = new PageLeecher("http://www.magic-ville.com/fr/rech_set.php");
		
		Vector<String> liens = new Vector<String>();
		
		String[] tableau = pl.getPageContent().split("href=set_cards.php\\?");
		
		for(int i = 1;i< tableau.length;i++){
			int positionChevron = -1;
			positionChevron = tableau[i].indexOf('>');
			if (positionChevron == -1)continue;
			tableau[i] = tableau[i].substring(0, positionChevron);
			liens.add(tableau[i]);
		}
		return liens;
	}
	
	public static Vector<Card> getCardsList(String setCode) {
		PageLeecher pl = new PageLeecher("http://www.magic-ville.com/fr/set_visual.php?lang=eng&setcode="+setCode);
		
		Vector<Card> cartes = new Vector<Card>();
		
		String[] tableau = pl.getPageContent().split("href=carte\\?");
		
		for(int i = 1;i< tableau.length;i++){
			if(i%2 == 1)continue;
			int positionEspace = -1;
			positionEspace = tableau[i].indexOf(' ');
			int positionChevron1 = -1;
			positionChevron1 = tableau[i].indexOf('>');
			int positionChevron2 = -1;
			positionChevron2 = tableau[i].indexOf('<');
			String cardReference = tableau[i].substring(0, positionEspace);
			String cardName = tableau[i].substring(positionChevron1+1, positionChevron2);
			
			System.out.println("Extraction de :"+cardReference+" "+cardName);
			Card card = extractCardInformation(cardReference,cardName);
			
			cartes.add(card);
		}
		return cartes;
	}

	private static Card extractCardInformation(String cardReference,
			String cardName) {
		PageLeecher pl = new PageLeecher("http://www.magic-ville.com/fr/carte.php?"+cardReference);
		
		//getRarity
		Card.Rarity cardRarity = Card.GetRarity(pl.getPageContent());
		
		//getType
		
		String[] tableau = pl.getPageContent().split("class=G12");
		int positionChevron1 = -1;
		positionChevron1 = tableau[2].indexOf('>');
		int positionChevron2 = -1;
		positionChevron2 = tableau[2].indexOf('<');
		
		String cardType = tableau[2].substring(positionChevron1+1,positionChevron2);

		return new Card(cardName,cardReference,cardType,cardRarity);
		
	}
}
