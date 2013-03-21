package binder;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBooster extends Remote{

	public void add(ICard card) throws RemoteException;
	public ICard get(int i) throws RemoteException;
	public int indexOf(ICard card) throws RemoteException;
	public void remove(ICard selectedCard) throws RemoteException;
	public int size() throws RemoteException;
	public void sortMe() throws RemoteException;

}