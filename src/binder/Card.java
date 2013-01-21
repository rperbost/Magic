package binder;

import java.util.ArrayList;
import java.util.Arrays;

import leecher.Extractor;

public class Card {
	public enum Rarity {
	    common, uncommon, rare, timeshifted, mythic, unknow 
	}
	public enum Land {
		plains,island,swamp,mountain,forest
	}
	Rarity rarity;
	
	String set;
	String index;
	String type;
	String name;
	
	public String getName() {
		return name;
	}
	public String getIndex() {
		return index;
	}
	public Rarity getRarity() {
		return rarity;
	}
	public String getSet(){
		return set;
	}
	
	public Card(String name,String reference,String type,Rarity rarity){
		this(name,
				reference.substring(0, 3),
				reference.substring(3),
				type,
				rarity
			);
	}
	
	public Card(String name,String set,String index,String type,Rarity rarity){
		this.name = name.trim();
		this.set = set.trim();
		this.index = index.trim();
		this.type = type.trim();
		this.rarity = rarity;
	}

	
	public static Card parseCsv(String ligneCsv) {
		ArrayList<String> cardAttributes = new ArrayList<String>(Arrays.asList(ligneCsv.split("\t")));
		return new Card(
				cardAttributes.get(0),
				cardAttributes.get(1),
				cardAttributes.get(2),
				cardAttributes.get(3),
				Card.parseRarity(cardAttributes.get(4))
		);
		
	}
	private static Rarity parseRarity(String rarityString) {
		Rarity r = Rarity.unknow;
		
		if(rarityString.equals("rare"))r = Rarity.rare;
		else if(rarityString.equals("uncommon"))r = Rarity.uncommon;
		else if(rarityString.equals("common"))r = Rarity.common;
		else if(rarityString.equals("timeshifted"))r = Rarity.timeshifted;
		else if(rarityString.equals("mythic"))r = Rarity.mythic;
		
		return r;
	}
	public String toString() {
		return this.set+this.index+"|"+this.name;
	}

	public boolean equals(Card card){
		return this.name.equals(card.name)
			&& this.set.equals(card.set)
			&& this.index.equals(card.index);
	}
	
	public Card clone(){
		return new Card(this.name,this.set,this.index,this.type,this.rarity);
	}

	public String getLilImageLink(){
		return getImageLink("lil");
	}
	public String getBigImageLink(){
		return getImageLink("big");
	}
	private String getImageLink(String size){
		return "pics/"+size+"/"+this.set+"/"+this.index+".jpg";
	}



	public static Rarity GetRarity(String pageContent) {
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
	
	public String getCsv(){
		String retour = "";
		retour+=this.name+"\t";
		retour+=this.set+"\t";
		retour+=this.index+"\t";
		retour+=this.type+"\t";
		retour+=this.rarity;
		return retour;
	}

}
