package server;

import java.util.ArrayList;
import java.util.List;

import player.IPlayer;

public class DraftRoom {

	List<IPlayer> otherPlayers;
	IPlayer owner;
	String sets[];
	int timer;
	
	public DraftRoom(IPlayer owner,String set1,String set2,String set3){
		this.owner = owner;
		this.otherPlayers = new ArrayList<IPlayer>();
		this.timer = 5000;
		sets = new String[3];
		sets[0]=set1;
		sets[1]=set2;
		sets[2]=set3;
	}
	
	public boolean canJoin(IPlayer p) {
		if(!otherPlayers.contains(p) && otherPlayers.size() < 7)return true;
		else
			return false;
	}
	
	public IPlayer getOwner(){
		return owner;
	}
	
	public int getOwnerId(){
		return owner.getId();
	}
	
	public ArrayList<IPlayer> getPlayers() {
		ArrayList<IPlayer> p = new ArrayList<IPlayer>();
		p.add(owner);
		for(IPlayer op : otherPlayers){
			p.add(op);
		}
		return p;
	}
	
	public String[] getSets() {
		return sets;
	}

	public boolean isDeletable(int id){
		if(owner.getId() == id){
			timer = 5000;
			return true;
		}
		return false;
	}

	public boolean join(IPlayer p){
		if(!otherPlayers.contains(p) && otherPlayers.size() < 7){
			otherPlayers.add(p);
			return true;
		}
		return false;
	}

	public boolean leave(IPlayer p){
		if(!owner.equals(p)){
			return otherPlayers.remove(p);
		}
		return false;
	}

	public String serialize() {
		String players = "";
		for(IPlayer p : otherPlayers){
			players+="&"+p.getId();
		}
		return owner.getId()+"&"+(otherPlayers.size()+1)+"&"+sets[0]+"&"+sets[1]+"&"+sets[2]+players;
	}
}
