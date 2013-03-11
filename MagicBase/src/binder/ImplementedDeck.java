package binder;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import binder.interfaces.Booster;
import binder.interfaces.Card;
import binder.interfaces.Deck;

@SuppressWarnings("serial")
public class ImplementedDeck extends UnicastRemoteObject implements Deck {
	private Booster mainDeck;
	private Booster sideboard;
	
	public ImplementedDeck() throws RemoteException{
		super();
		this.mainDeck = new ImplementedBooster();
		this.sideboard = new ImplementedBooster();
	}

	public void addToDeck(Card card) throws RemoteException{
		mainDeck.add(card);
		mainDeck.sortMe();
	}

	public void addToSideboard(Card card) throws RemoteException{
		sideboard.add(card);
		sideboard.sortMe();
	}
	
	public Booster getMainDeck() {
		return mainDeck;
	}

	public Booster getSideboard() {
		return sideboard;
	}
	
	public void removeFromDeck(Card card) throws RemoteException{
		mainDeck.remove(card);
	}

	public void removeFromSideboard(Card card) throws RemoteException{
		sideboard.remove(card);
	}


	@Override
	public String toCodString() throws RemoteException {
		String retour = "MAINDECK\n";
		for(int i = 0; i < mainDeck.size();i++){
			retour += "["+mainDeck.get(i).getSet()+"] ";
			retour += mainDeck.get(i).getName()+"\n";
		}
		retour += "\n";
		retour += "SIDEBOARD\n";
		for(int i = 0; i < sideboard.size();i++){
			retour += "["+sideboard.get(i).getSet()+"] ";
			retour += sideboard.get(i).getName()+"\n";
		}
		return retour;
	}

	public void transferCard(Card card, String source, String target) throws RemoteException{
				
		boolean cardMoved = false;
		if(target.equals("SIDEBOARD")){
			addToSideboard(card);
			cardMoved=true;
		}
		if(target.equals("DECK")){
			addToDeck(card);
			cardMoved=true;
		}
		if(target.equals("TRASH")){
			cardMoved=true;
		}
		
		if(source.equals("SIDEBOARD")&&cardMoved){
			removeFromSideboard(card);
		}
		if(source.equals("DECK")&&cardMoved){
			removeFromDeck(card);
		}
		
	}
}
