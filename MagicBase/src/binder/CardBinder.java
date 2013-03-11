package binder;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import binder.interfaces.Binder;
import binder.interfaces.Card;

@SuppressWarnings("serial")
public class CardBinder extends UnicastRemoteObject implements Binder {

	ArrayList<Card> cards;
	
	protected CardBinder() throws RemoteException {
		super();
		this.cards = new ArrayList<Card>();
	}

	@Override
	public void addCard(Card card) throws RemoteException {
		if(!cards.contains(card))cards.add(card);
	}

	@Override
	public boolean contains(Card card) throws RemoteException {
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
	public Card get(int i) throws RemoteException {
		return cards.get(i);
	}

	@Override
	public Card get(String name) throws RemoteException {
		for(int i = 0;i < cards.size(); i++){
			if(cards.get(i).getName().equals(name))return cards.get(i);
		}
		return null;
	}

	@Override
	public Card get(String set, String id) throws RemoteException {
		return getByRef(set+id);
	}

	@Override
	public Card getByRef(String ref) throws RemoteException {
		for(int i = 0;i < cards.size(); i++){
			if(cards.get(i).getReference().equals(ref))return cards.get(i);
		}
		return null;
	}

	@Override
	public Binder getSet(String set) throws RemoteException {
		CardBinder binder = new CardBinder();
		for(int i = 0;i < cards.size(); i++){
			if(cards.get(i).getSet().equals(set))binder.addCard(cards.get(i));
		}
		return binder;
	}

	@Override
	public List<String> getSetList() throws RemoteException {
		List<String> sets = new ArrayList<String>();
		for(Card c:cards){
			if(!sets.contains(c.getSet()))sets.add(c.getSet());
		}
		return sets;
	}

	@Override
	public int size() throws RemoteException {
		return cards.size();
	}

}
