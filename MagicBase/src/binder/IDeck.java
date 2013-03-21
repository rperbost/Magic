package binder;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDeck extends Remote{

	public void addToDeck(ICard card) throws RemoteException;
	public void addToSideboard(ICard card) throws RemoteException;
	public IBooster getMainDeck() throws RemoteException;
	public IBooster getSideboard() throws RemoteException;
	public void removeFromDeck(ICard card) throws RemoteException;
	public void removeFromSideboard(ICard card) throws RemoteException;
	public String toCodString() throws RemoteException;
	public void transferCard(ICard card, String source, String target) throws RemoteException;

}