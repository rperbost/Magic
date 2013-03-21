package binder;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


@SuppressWarnings("serial")
public class Card extends UnicastRemoteObject implements ICard {
	
	public static Rarity GetRarity(String pageContent)  throws RemoteException{
		String before = "graph/rarity/carte";
		String after = ".gif";
		
		Rarity rarity = Rarity.unknow;
		
		if(pageContent.contains(before+"30"+after)){
			rarity = Rarity.common;
		}else if(pageContent.contains(before+"20"+after)){
			rarity = Rarity.uncommon;
		}else if(pageContent.contains(before+"10"+after)){
			rarity = Rarity.rare;
		}else if(pageContent.contains(before+"4"+after)){
			rarity = Rarity.mythic;
		}else  if(pageContent.contains(before+"5"+after)){
			rarity = Rarity.timeshifted;
		}
		return rarity;
	}
	
	
	public static Rarity parseRarity(String rarityString)  throws RemoteException{
		Rarity r = Rarity.unknow;
		
		if(rarityString.equals("rare"))r = Rarity.rare;
		else if(rarityString.equals("uncommon"))r = Rarity.uncommon;
		else if(rarityString.equals("common"))r = Rarity.common;
		else if(rarityString.equals("timeshifted"))r = Rarity.timeshifted;
		else if(rarityString.equals("mythic"))r = Rarity.mythic;
		
		return r;
	}
	String index;
	String name;
	
	Rarity rarity;

	String set;
	
	String type;
	
	public Card(String name,String reference,String type,Rarity rarity) throws RemoteException{
		this(name,
				reference.substring(0, 3),
				reference.substring(3),
				type,
				rarity
			);
	}

	public Card(String name,String set,String index,String type,Rarity rarity) throws RemoteException{
		super();
		this.name = name.trim();
		this.set = set.trim();
		this.index = index.trim();
		this.type = type.trim();
		this.rarity = rarity;
	}
	
	public ICard clone(){
		try {
			return new Card(this.name,this.set,this.index,this.type,this.rarity);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean equals(ICard card) throws RemoteException{
		return this.name.equals(card.getName())
			&& this.set.equals(card.getSet())
			&& this.index.equals(card.getIndex());
	}
	
	public String getIndex()  throws RemoteException{
		return index;
	}

	public String getName()  throws RemoteException{
		return name;
	}

	public Rarity getRarity()  throws RemoteException{
		return rarity;
	}

	public String getReference() throws RemoteException{
		return set+index;
	}

	public String getSet() throws RemoteException{
		return set;
	}

	@Override
	public String getType() throws RemoteException {
		return type;
	}

	public String toString() {
		try {
			return this.getReference();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return "";
	}

}
