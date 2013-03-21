package leecher;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import datamanager.IDatabaseManager;

import binder.Card;
import binder.IBinder;
import binder.ICard;

public class MagicVilleLeecher implements ILeecher{

	@Override
	public void execute(IBinder binder, IDatabaseManager databaseManager) throws RemoteException{
		long debutTraitement = System.currentTimeMillis();
		Vector<String> editionsIds = this.getEditions();
		int i = 0;
		for(String editionId:editionsIds){
			System.out.println("["+(System.currentTimeMillis() - debutTraitement)+"]traitement de "+(i++)+" : "+editionId);
			int nombreDeCartesAjoutees = this.extractCards(binder,editionId);
			if(nombreDeCartesAjoutees>0)databaseManager.save(binder);
		}
		System.out.println("fin");
	}

	private Card extractCardInformation(String cardReference,
			String cardName) throws RemoteException {
		PageRetriever pl = new PageRetriever("http://www.magic-ville.com/fr/carte.php?"+cardReference);
		
		//getRarity
		ICard.Rarity cardRarity = Card.GetRarity(pl.getPageContent());
		
		//getType
		
		String[] tableau = pl.getPageContent().split("class=G12");
		int positionChevron1 = -1;
		positionChevron1 = tableau[2].indexOf('>');
		int positionChevron2 = -1;
		positionChevron2 = tableau[2].indexOf('<');
		
		String cardType = tableau[2].substring(positionChevron1+1,positionChevron2);

		return new Card(cardName,cardReference,cardType,cardRarity);
		
	}

	private int extractCards(IBinder binder, String editionId) throws RemoteException {
		PageRetriever pl = new PageRetriever("http://www.magic-ville.com/fr/set_visual.php?lang=eng&setcode="+editionId);
		
		String[] tableau = pl.getPageContent().split("href=carte\\?");
		
		int nombreDeCartesAjoutees = 0;
		if(tableau.length/2 < 100){
			System.out.println("Pas assez de cartes dans l'edition pour être utilisable dans un draft (<100).");
			return 0;
		}
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
			if(binder.getByRef(cardReference) == null){
				System.out.println("Extraction de :"+cardReference+" "+cardName);
				Card card = extractCardInformation(cardReference,cardName);
				binder.addCard(card);
				nombreDeCartesAjoutees++;
			}
		}
		return nombreDeCartesAjoutees;
	}
	
	public Vector<String> getEditions() {
		PageRetriever pl = new PageRetriever("http://www.magic-ville.com/fr/rech_set.php");
		
		Vector<String> editionsIds = new Vector<String>();
		
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
			
			editionsIds.add(tableau.get(i));
		}
		return editionsIds;
	}
}
