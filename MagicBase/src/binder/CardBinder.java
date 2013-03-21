package binder;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("serial")
public class CardBinder extends UnicastRemoteObject implements IBinder {

	ArrayList<ICard> cards;
	
	public CardBinder() throws RemoteException {
		super();
		this.cards = new ArrayList<ICard>();
	}

	@Override
	public void addCard(ICard card) throws RemoteException {
		if(!cards.contains(card))cards.add(card);
	}

	@Override
	public boolean contains(ICard card) throws RemoteException {
		return cards.contains(card);
	}

	@Override
	public boolean contains(String name) throws RemoteException {
		for(int i = 0;i < cards.size(); i++){
			if(cards.get(i).getName().equals(name))return true;
		}
		return false;
	}

	@Override
	public ICard get(int i) throws RemoteException {
		return cards.get(i);
	}

	@Override
	public ICard get(String name) throws RemoteException {
		for(int i = 0;i < cards.size(); i++){
			if(cards.get(i).getName().equals(name))return cards.get(i);
		}
		return null;
	}

	@Override
	public ICard get(String set, String id) throws RemoteException {
		return getByRef(set+id);
	}

	@Override
	public ICard getByRef(String ref) throws RemoteException {
		for(int i = 0;i < cards.size(); i++){
			if(cards.get(i).getReference().equals(ref))return cards.get(i);
		}
		return null;
	}

	@Override
	public IBinder getSet(String set) throws RemoteException {
		CardBinder binder = new CardBinder();
		for(int i = 0;i < cards.size(); i++){
			if(cards.get(i).getSet().equals(set))binder.addCard(cards.get(i));
		}
		return binder;
	}

	@Override
	public List<String> getSetList() throws RemoteException {
		List<String> sets = new ArrayList<String>();
		for(ICard c:cards){
			if(!sets.contains(c.getSet()))sets.add(c.getSet());
		}
		return sets;
	}

	@Override
	public int size() throws RemoteException {
		return cards.size();
	}

}
