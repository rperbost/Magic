package ihm.screen;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import rmi.MagicRemoteManager;


public class ServerListParser {
	public List< List<String> > servers;
	public String serverStringList;
	public ServerListParser(String serverStringList){
		this.serverStringList = serverStringList;
		servers = new ArrayList< List<String> >();
		String splited[] = serverStringList.split("\t");
		for(int i =0; i < splited.length && !this.serverStringList.equals(""); i++){
			servers.add(new ArrayList<String>());
			String[] splited2 = splited[i].split("&");
			for(int j =0; j < splited2.length ; j++){
				servers.get(i).add(splited2[j]);
			}
		}
	}

	public int getMyActualRoom() throws RemoteException{
		for(int i = 0; i < size();i++){
			if(imIn(i))return i;
		}
		return -1;
	}
	
	public List<String> getOtherPlayers(int roomId) {
		List<String> otherPlayers = new ArrayList<String>();
		for(int i = 5;i < servers.get(roomId).size();i++){
			otherPlayers.add(servers.get(roomId).get(i));
		}
		return otherPlayers;
	}
	public int getOwnedRoom() throws RemoteException{
		for(int i = 0; i < size();i++){
			if(imOwner(i))return i;
		}
		return -1;
	}
	public int getOwner(int i) {
		return Integer.parseInt(servers.get(i).get(0));
	}
	
	public String[] getSets(int serverId){
		String[] s = new String[3];
		s[0]=servers.get(serverId).get(2);
		s[1]=servers.get(serverId).get(3);
		s[2]=servers.get(serverId).get(4);
		
		return s;
	}

	public String getSetsAsString(int i) {
		String[] sets = getSets(i);
		return "["+sets[0]+","+sets[1]+","+sets[2]+"]";
	}

	public boolean imIn(int roomId) throws RemoteException{
		String myId = MagicRemoteManager.getInstance().getId()+"";
		for(String r:getOtherPlayers(roomId)){
			if(r.equals(myId))return true;
		}
		return imOwner(roomId);
	}
	
	public boolean imOwner(int room) throws RemoteException{
		return MagicRemoteManager.getInstance().getId() == getOwner(room);
	}
	
	public boolean isDeletable(int serverId) throws RemoteException{
		return((MagicRemoteManager.getInstance().getId()+"").equals(servers.get(serverId).get(0)));
	}
	
	public int size(){
		return servers.size();
	}
	
	public int size(int serverId){
		return Integer.parseInt(servers.get(serverId).get(1));
	}

	public String toString(int roomId) {
		return "Draft #"+roomId+" "+getSetsAsString(roomId).toString()+" : "+size(roomId)+"/8";
	}
}
