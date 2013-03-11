package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import binder.interfaces.Deck;

public interface ClientCallBack extends Remote{
	boolean ping() throws RemoteException;

	void refresh() throws RemoteException;

	void startDeckListScreen(Deck deck) throws RemoteException;

	void startDraftScreen() throws RemoteException;
}
