package server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import player.IPlayer;

public class DraftList {
	private class CheckClients extends Thread{
		DraftList draftList;
		
		public CheckClients(DraftList draftList){
			this.draftList = draftList;
		}
		
		public void run(){
			while(true){
				boolean flag = false;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
				//check if owner still connected
				List<DraftRoom> toDelete = new ArrayList<DraftRoom>();
				for(DraftRoom dr:draftList.getRooms()){
					try{
						dr.getOwner().ping();
					}catch(Exception e){
						toDelete.add(dr);
						flag = true;
					}
				}
				for(DraftRoom dr:toDelete){
					draftList.getRooms().remove(dr);
				}
				
				//remove unconnected players
				for(DraftRoom dr:draftList.getRooms()){
					List<IPlayer> unreachableOtherPlayers = new ArrayList<IPlayer>();
					for(IPlayer p:dr.otherPlayers){
						try{
							p.ping();
						}catch(Exception e){
							unreachableOtherPlayers.add(p);
						}
					}
					for(IPlayer p: unreachableOtherPlayers){
						dr.otherPlayers.remove(p);
						flag = true;
					}
				}
				
				if(flag){
					try {
						Server.getInstance().refreshClients();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	
	
	List<DraftRoom> draftList;
	public DraftList(){
		draftList = new ArrayList<DraftRoom>();
		(new CheckClients(this)).start();
	}
	
	public void createDraft(int id,String set1,String set2,String set3){
		draftList.add(new DraftRoom(Server.getPlayer(id),set1,set2,set3));
	}
	public void deleteDraft(int roomId) {
		DraftRoom toDelete = null;
		for(DraftRoom dr:draftList){
			if(dr.getOwnerId() == roomId){
				toDelete = dr;
			}
		}
		if(toDelete!=null)draftList.remove(toDelete);
	}
	public DraftRoom get(int id){
		for(DraftRoom dr:draftList){
			if(dr.getOwnerId() == id) return dr;
		}
		return null;
	}

	public List<DraftRoom> getRooms() {
		return draftList;
	}

	public void joinDraft(int roomId,int id){
		this.get(roomId).join(Server.getPlayer(id));
	}

	public void leaveDraft(int id, int roomId) {
		this.get(roomId).otherPlayers.remove(Server.getPlayer(id));	
	}
}
