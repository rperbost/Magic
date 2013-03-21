package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import datamanager.CsvManager;
import datamanager.IDatabaseManager;
import datamanager.XmlManager;

import leecher.ILeecher;
import leecher.MagicVilleLeecher;

import player.HumanPlayer;
import player.IPlayer;

import binder.*;

import rmi.IServer;


@SuppressWarnings("serial")
public class Server extends UnicastRemoteObject implements IServer {

	public static List<IPlayer> clients = new ArrayList<IPlayer>();
	
	private static Server theInstance = null;
	
	public static Server getInstance() throws RemoteException{
		if(theInstance == null){
			theInstance = new Server();
		}
		return theInstance;
	}
	public static IPlayer getPlayer(int id) {
		for(IPlayer c:clients){
			if(c.getId()==id)return c;
		}
		return null;
	}
	
	public static void main(String [] args) throws RemoteException{
		Server.getInstance();
	}
	DraftList draftList = new DraftList();

	
	private SetBinder masterBinder = null;

	private Server() throws RemoteException {
		super();

		masterBinder = new SetBinder();
		IDatabaseManager databaseManager = new XmlManager();
		ILeecher leecher = new MagicVilleLeecher();
		
		databaseManager.load(masterBinder);
		
		leecher.execute(masterBinder,databaseManager);
		
		databaseManager.save(masterBinder);
		(new CsvManager()).save(masterBinder);
		
		try {
			Naming.rebind("//localhost:2020/MagicServer", this );
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		System.out.println("fin init server");
	}
	
	@Override
	public void createDraftRoom(int id,String set1,String set2,String set3) throws RemoteException {
		draftList.createDraft(id, set1, set2, set3);
		refreshClients();
	}


	@Override
	public void deleteDraftRoom(int id) throws RemoteException {
		draftList.deleteDraft(id);
		refreshClients();
	}


	public IBinder getBinder() throws RemoteException {
		return masterBinder;
	}

	@Override
	public String getDraftList() throws RemoteException {
		String r = "";
		for(DraftRoom dr:draftList.getRooms()){
			r+=dr.serialize()+"\t";
		}
		if (!r.equals("")){
			r = r.substring(0, r.length()-1);
		}
		return r;
		//return "1&8&13m&13m&13m\t1&2&13m&13m&13m\t1&8&13m&13m&13m\t1&2&13m&13m&13m\t1&2&13m&13m&13m";
	}


	@Override
	public String getDraftState(int id) throws RemoteException {
		for(IPlayer c :clients ){
			if(c.getId() == id){
				if(c.getInProgressDraft() != null){
					return c.getInProgressDraft().getSerializedData(id);
				}
			}
		}
		return "";
	}


	@Override
	public void joinDraft(int id, int draftId) throws RemoteException {
		draftList.joinDraft(draftId, id);
		refreshClients();
	}


	@Override
	public void leaveDraftRoom(int id, int roomId) throws RemoteException {
		draftList.leaveDraft(id,roomId);
		refreshClients();
	}


	public void refreshClients() throws RemoteException{
		List<IPlayer> unreachable = new ArrayList<IPlayer>();
		for(IPlayer c:clients){
			try{
				c.refresh();
			}catch(Exception e){
				unreachable.add(c);
			}
		}
		for(IPlayer c:unreachable){
			clients.remove(c);
		}
	}

	public int registerMe() throws RemoteException {
		clients.add(new HumanPlayer("#"+clients.size(),clients.size()));
		System.out.println("clientRegistered #"+clients.size());
		return clients.size()-1;
	}


	@Override
	public void selectCard(int id, ICard card) throws RemoteException {
		for(IPlayer c :clients ){
			if(c.getId() == id){
				if(c.getInProgressDraft() != null){
					c.getInProgressDraft().selectCard(id,card);
				}
			}
		}		
	}
	
	@Override
	public void startDraft(int id) throws RemoteException {
		
		DraftRoom dr = draftList.get(id);
		DraftThread draftThread = new DraftThread(dr.getOwnerId(),dr.getPlayers(),dr.getSets());
		
		for(IPlayer c:clients){
			c.setInProgressDraft(draftThread);
		}
		
		draftThread.start();
		
		draftList.deleteDraft(id);
		refreshClients();
	}
}