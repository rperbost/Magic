package rmi;

import java.rmi.Remote;

import java.rmi.RemoteException;

import binder.interfaces.Binder;
import binder.interfaces.Card;

public interface Repartiteur extends Remote {

	public void createDraftRoom(int id, String set1, String set2, String set3) throws RemoteException;

	public void deleteDraftRoom(int id) throws RemoteException;

	public Binder getBinder() throws RemoteException;

	public String getDraftList() throws RemoteException;

	public String getDraftState(int id) throws RemoteException;
	
	public void joinDraft(int id, int draftId) throws RemoteException;
	
	public void leaveDraftRoom(int id, int roomId) throws RemoteException;
	
	public int registerMe() throws RemoteException;

	public void selectCard(int id, Card card) throws RemoteException;

	public void startDraft(int id) throws RemoteException;

}
