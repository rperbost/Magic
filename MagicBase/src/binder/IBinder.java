package binder;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IBinder extends Remote{
	public void addCard(ICard card) throws RemoteException;
	public boolean contains(ICard card) throws RemoteException;
	public boolean contains(String name) throws RemoteException;
	public ICard get(int i) throws RemoteException;
	public ICard get(String name) throws RemoteException;
	public ICard get(String set, String id) throws RemoteException;
	public ICard getByRef(String ref) throws RemoteException;
	public IBinder getSet(String set) throws RemoteException;
	public List<String> getSetList() throws RemoteException;
	public int size() throws RemoteException;
}
