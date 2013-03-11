package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import player.Human;
import player.Player;

import binder.*;
import binder.interfaces.Binder;
import binder.interfaces.Card;

import rmi.Repartiteur;


@SuppressWarnings("serial")
public class Server extends UnicastRemoteObject implements Repartiteur {

	public static List<Player> clients = new ArrayList<Player>();
	
	private static Server theInstance = null;
	
	public static Server getInstance() throws RemoteException{
		if(theInstance == null){
			theInstance = new Server();
		}
		return theInstance;
	}
	public static Player getPlayer(int id) {
		for(Player c:clients){
			if(c.getId()==id)return c;
		}
		return null;
	}
	
	DraftList draftList = new DraftList();
	private SetBinder masterBinder = null;

	
	private Server() throws RemoteException {
		super();

		masterBinder = new SetBinder();
		File f = new File("sets/");
		ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
		
		for(int i = 0;i < files.size(); i++){
			this.addFile(files.get(i));
		}
	}

	private void addFile(File file) {
		try {
			BufferedReader lignes = new BufferedReader(new FileReader(file));
			
			String ligne;
			
			try {
				while((ligne = lignes.readLine()) != null){
					masterBinder.addCard(ImplementedCard.parseCsv(ligne));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
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


	public Binder getBinder() throws RemoteException {
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
		for(Player c :clients ){
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
		List<Player> unreachable = new ArrayList<Player>();
		for(Player c:clients){
			try{
				c.refresh();
			}catch(Exception e){
				unreachable.add(c);
			}
		}
		for(Player c:unreachable){
			clients.remove(c);
		}
	}


	public int registerMe() throws RemoteException {
		clients.add(new Human("#"+clients.size(),clients.size()));
		return clients.size()-1;
	}

	@Override
	public void selectCard(int id, Card card) throws RemoteException {
		for(Player c :clients ){
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
		
		for(Player c:clients){
			c.setInProgressDraft(draftThread);
		}
		
		draftThread.start();
		
		draftList.deleteDraft(id);
		refreshClients();
	}
}