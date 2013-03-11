package binder.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Booster extends Remote{

	public void add(Card card) throws RemoteException;
	public Card get(int i) throws RemoteException;
	public int indexOf(Card card) throws RemoteException;
	public void remove(Card selectedCard) throws RemoteException;
	public int size() throws RemoteException;
	public void sortMe() throws RemoteException;

}