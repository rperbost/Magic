package binder;

import java.util.ArrayList;
import java.util.Collections;

public class Booster {
	ArrayList<Card> booster;
	
	public Booster(ArrayList<Card> booster){
		this.booster = booster;
	}

	public Booster() {
		this.booster = new ArrayList<Card>();
	}

	public void remove(Card selectedCard) {
		booster.remove(selectedCard);		
	}

	public int size() {
		return booster.size();
	}

	public Card get(int i) {
		return booster.get(i);
	}
	
	public int indexOf(Card card){
		return this.booster.indexOf(card);
	}
	
	public void add(Card card){
		booster.add(card);
	}
	
	public void sortMe(){
		boolean flag=false;
		do{
			flag=false;
			
			for(int i = 0; i < booster.size()-1; i++){
				if(booster.get(i).getName().compareTo(booster.get(i+1).getName())>0){
					Collections.swap(booster,i,i+1);
					flag=true;
				}
			}
			
		}while(flag);
	}
	
}
