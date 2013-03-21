package binder;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


@SuppressWarnings("serial")
public class Deck extends UnicastRemoteObject implements IDeck {
	private IBooster mainDeck;
	private IBooster sideboard;
	
	public Deck() throws RemoteException{
		super();
		this.mainDeck = new Booster();
		this.sideboard = new Booster();
	}

	public void addToDeck(ICard card) throws RemoteException{
		mainDeck.add(card);
		mainDeck.sortMe();
	}

	public void addToSideboard(ICard card) throws RemoteException{
		sideboard.add(card);
		sideboard.sortMe();
	}
	
	public IBooster getMainDeck() {
		return mainDeck;
	}

	public IBooster getSideboard() {
		return sideboard;
	}
	
	public void removeFromDeck(ICard card) throws RemoteException{
		mainDeck.remove(card);
	}

	public void removeFromSideboard(ICard card) throws RemoteException{
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

	public void transferCard(ICard card, String source, String target) throws RemoteException{
				
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
