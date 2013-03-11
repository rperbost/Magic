package server;

import java.util.ArrayList;
import java.util.List;

import player.Player;

public class DraftRoom {

	List<Player> otherPlayers;
	Player owner;
	String sets[];
	int timer;
	
	public DraftRoom(Player owner,String set1,String set2,String set3){
		this.owner = owner;
		this.otherPlayers = new ArrayList<Player>();
		this.timer = 5000;
		sets = new String[3];
		sets[0]=set1;
		sets[1]=set2;
		sets[2]=set3;
	}
	
	public boolean canJoin(Player p) {
		if(!otherPlayers.contains(p) && otherPlayers.size() < 7)return true;
		else
			return false;
	}
	
	public Player getOwner(){
		return owner;
	}
	
	public int getOwnerId(){
		return owner.getId();
	}
	
	public ArrayList<Player> getPlayers() {
		ArrayList<Player> p = new ArrayList<Player>();
		p.add(owner);
		for(Player op : otherPlayers){
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

	public boolean join(Player p){
		if(!otherPlayers.contains(p) && otherPlayers.size() < 7){
			otherPlayers.add(p);
			return true;
		}
		return false;
	}

	public boolean leave(Player p){
		if(!owner.equals(p)){
			return otherPlayers.remove(p);
		}
		return false;
	}

	public String serialize() {
		String players = "";
		for(Player p : otherPlayers){
			players+="&"+p.getId();
		}
		return owner.getId()+"&"+(otherPlayers.size()+1)+"&"+sets[0]+"&"+sets[1]+"&"+sets[2]+players;
	}
}
