package leecher;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import binder.ImplementedCard;
import binder.interfaces.Card;

public class MagicVilleLeecher {

	private static ImplementedCard extractCardInformation(String cardReference,
			String cardName) throws RemoteException {
		PageLeecher pl = new PageLeecher("http://www.magic-ville.com/fr/carte.php?"+cardReference);
		
		//getRarity
		Card.Rarity cardRarity = ImplementedCard.GetRarity(pl.getPageContent());
		
		//getType
		
		String[] tableau = pl.getPageContent().split("class=G12");
		int positionChevron1 = -1;
		positionChevron1 = tableau[2].indexOf('>');
		int positionChevron2 = -1;
		positionChevron2 = tableau[2].indexOf('<');
		
		String cardType = tableau[2].substring(positionChevron1+1,positionChevron2);

		return new ImplementedCard(cardName,cardReference,cardType,cardRarity);
		
	}
	
	public static Vector<ImplementedCard> getCardsList(String setCode) throws RemoteException {
		PageLeecher pl = new PageLeecher("http://www.magic-ville.com/fr/set_visual.php?lang=eng&setcode="+setCode);
		
		Vector<ImplementedCard> cartes = new Vector<ImplementedCard>();
		
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
			ImplementedCard card = extractCardInformation(cardReference,cardName);
			
			cartes.add(card);
		}
		return cartes;
	}

	public static Vector<String> getEditions() {
		PageLeecher pl = new PageLeecher("http://www.magic-ville.com/fr/rech_set.php");
		
		Vector<String> liens = new Vector<String>();
		
		String[] tableau_ = pl.getPageContent().split("href=set_cards.php\\?");
		String[] tableau2_ = pl.getPageContent().split("href=set_visual.php\\?setcode=");
		
		List<String> tableau = new ArrayList<String>();
		tableau.addAll(Arrays.asList(tableau_));
		tableau.addAll(Arrays.asList(tableau2_));
		
		for(int i = 1;i< tableau.size();i++){
			int positionChevron = -1;
			positionChevron = tableau.get(i).indexOf('>');
			if (positionChevron == -1)continue;
			tableau.set(i,tableau.get(i).substring(0, positionChevron));
			int positionCommercialAnd = -1;
			positionCommercialAnd = tableau.get(i).indexOf('&');
			if (positionCommercialAnd != -1){
				tableau.set(i,tableau.get(i).substring(0, positionCommercialAnd));
			}
			if(!tableau.get(i).matches("[0-9]*"))continue;
			liens.add(tableau.get(i));
		}
		return liens;
	}
}
