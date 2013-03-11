package binder.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Deck extends Remote{

	public void addToDeck(Card card) throws RemoteException;
	public void addToSideboard(Card card) throws RemoteException;
	public Booster getMainDeck() throws RemoteException;
	public Booster getSideboard() throws RemoteException;
	public void removeFromDeck(Card card) throws RemoteException;
	public void removeFromSideboard(Card card) throws RemoteException;
	public String toCodString() throws RemoteException;
	public void transferCard(Card card, String source, String target) throws RemoteException;

}