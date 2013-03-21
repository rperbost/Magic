package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import binder.IDeck;

public interface IClient extends Remote{
	boolean ping() throws RemoteException;

	void refresh() throws RemoteException;

	void startDeckListScreen(IDeck deck) throws RemoteException;

	void startDraftScreen() throws RemoteException;
}
