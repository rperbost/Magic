package rmi;

import ihm.MainFrame;
import ihm.screen.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmi.ClientCallBack;
import rmi.Repartiteur;

import binder.interfaces.Binder;
import binder.interfaces.Card;
import binder.interfaces.Deck;

@SuppressWarnings("serial")
public class MagicRemoteManager extends UnicastRemoteObject implements ClientCallBack{
	static MagicRemoteManager theInstance = null;
	
	static public MagicRemoteManager getInstance() throws RemoteException{
		if(theInstance == null){
			theInstance = new MagicRemoteManager();
		}
		return theInstance;
	}
	
	private int id = -1;
	
	private Repartiteur server = null;
	private MagicRemoteManager() throws RemoteException{
		super();
		getId();
	}
	
	public void createDraftRoom(String set1, String set2, String set3) throws RemoteException {
		getServer().createDraftRoom(getId(),set1,set2,set3);		
	}
	
	public void deleteDraftRoom(int roomId) throws RemoteException {
		 getServer().deleteDraftRoom(roomId);
		
	}
	public Binder getBinder(){
		
		try {
			return getServer().getBinder();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public DraftState getDraftState() throws RemoteException{
		return new DraftState(getServer().getDraftState(getId()));
	}

	public int getId(){
		if(id == -1){
			try {
				id=getServer().registerMe();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		try {
			Naming.rebind("//localhost:2020/MagicClient_"+id, this );
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	
	public Repartiteur getServer(){
		if (server == null){
			try {
				server = (Repartiteur)Naming.lookup("//localhost:2020/MagicServer");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
		}
		return server;
	}
	
	public String getServerList() throws RemoteException {
		return getServer().getDraftList();
	}

	public void joinDraftRoom(int roomId) throws RemoteException {
		 getServer().joinDraft(getId(),roomId);
	}

	public void leaveDraftRoom(int roomId) throws RemoteException {
		getServer().leaveDraftRoom(getId(),roomId);
	}

	@Override
	public boolean ping() throws RemoteException {
		return true;
	}

	@Override
	public void refresh() throws RemoteException {
		try{
			MainFrame.getInstance().getScreen().refresh();
		}catch(Exception e){
			
		}
	}

	public void selectCard(Card card) throws RemoteException{
		getServer().selectCard(getId(),card);
	}

	@Override
	public void startDeckListScreen(Deck deck) throws RemoteException {
		MainFrame.getInstance().addScreen( "DECK" , new DeckScreen(deck) ).activeScreen("DECK");
	}

	public void startDraft() throws RemoteException {
		getServer().startDraft(getId());
	}

	@Override
	public void startDraftScreen() throws RemoteException {
		MainFrame.getInstance().addScreen( "DRAFT" , new DraftScreen() ).activeScreen("DRAFT");
	}
	
}
