package binder.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Binder extends Remote{
	public void addCard(Card card) throws RemoteException;
	public boolean contains(Card card) throws RemoteException;
	public boolean contains(String name) throws RemoteException;
	public Card get(int i) throws RemoteException;
	public Card get(String name) throws RemoteException;
	public Card get(String set, String id) throws RemoteException;
	public Card getByRef(String ref) throws RemoteException;
	public Binder getSet(String set) throws RemoteException;
	public List<String> getSetList() throws RemoteException;
	public int size() throws RemoteException;
}
