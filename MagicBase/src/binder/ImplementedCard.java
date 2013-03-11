package binder;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;

import binder.interfaces.Card;

@SuppressWarnings("serial")
public class ImplementedCard extends UnicastRemoteObject implements Card {
	
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
	
	public static ImplementedCard parseCsv(String ligneCsv)  throws RemoteException{
		ArrayList<String> cardAttributes = new ArrayList<String>(Arrays.asList(ligneCsv.split("\t")));
		return new ImplementedCard(
				cardAttributes.get(0),
				cardAttributes.get(1),
				cardAttributes.get(2),
				cardAttributes.get(3),
				ImplementedCard.parseRarity(cardAttributes.get(4))
		);
		
	}
	private static Rarity parseRarity(String rarityString)  throws RemoteException{
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
	
	public ImplementedCard(String name,String reference,String type,Rarity rarity) throws RemoteException{
		this(name,
				reference.substring(0, 3),
				reference.substring(3),
				type,
				rarity
			);
	}

	public ImplementedCard(String name,String set,String index,String type,Rarity rarity) throws RemoteException{
		super();
		this.name = name.trim();
		this.set = set.trim();
		this.index = index.trim();
		this.type = type.trim();
		this.rarity = rarity;
	}
	
	public Card clone(){
		try {
			return new ImplementedCard(this.name,this.set,this.index,this.type,this.rarity);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean equals(Card card) throws RemoteException{
		return this.name.equals(card.getName())
			&& this.set.equals(card.getSet())
			&& this.index.equals(card.getIndex());
	}

	
	public String getCsv() throws RemoteException{
		String retour = "";
		retour+=this.name+"\t";
		retour+=this.set+"\t";
		retour+=this.index+"\t";
		retour+=this.type+"\t";
		retour+=this.rarity;
		return retour;
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

	public String toString() {
		try {
			return this.getReference();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return "";
	}

}
