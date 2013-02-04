package binder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CardBinder implements Binder{
	Map< Character , ArrayList<Card> > cards;
	
	public CardBinder(){
		cards = new HashMap < Character, ArrayList<Card> >();
	}

	public void addCard(Card card){
		String cardName = card.getName();
		
		if(cardName.length() == 0)return;
		
		Character c = new Character(card.getName().charAt(0));
		
		ArrayList<Card> binderPart = null;
	
		if(!this.contains(card)){
			if(!this.cards.containsKey(c)){
				this.cards.put(c, new ArrayList<Card>());
			}
			binderPart = this.cards.get(c);
			binderPart.add(card.clone());
		}
	}

	public boolean contains(Card card) {
		if(getCard(card) == null) return false;
		else return true;
	}
	
	@Override
	public boolean contains(String name) {
		if(get(name)==null)return false;
		else return true;
	}

	public Card get(int i){
		Iterator<Character> it = cards.keySet().iterator();
				
		while(it.hasNext()){
			Character c = it.next();
			int size = cards.get(c).size();
			if(i >= size){
				i-=size;
			}else{
				return cards.get(c).get(i);
			}
		}
	
		return null;	
	}

	@Override
	public Card get(String name) {
		Character c = new Character(name.charAt(0));
		ArrayList<Card>binderPart = cards.get(c);
		
		for(int i = 0;i < binderPart.size();i++){
			if(binderPart.get(i).getName().equals(name)){
				return binderPart.get(i);
			}
		}
		
		return null;
	}

	public Card get(String ref, String id) {
		return getByRef(ref+id);
	}

	public Card getByRef(String ref) {
		for(int i = 0; i < this.size();i++){
			Card c = this.get(i);
			if(c.getReference().equals(ref)){
				return c;
			}
		}
		return null;
	}

	public int size(){
		Iterator<Character> it = cards.keySet().iterator();
		
		int size = 0;
		
		while(it.hasNext()){
			size += cards.get(it.next()).size();
		}
		
		return size;
	}
	
	private Card getCard(Card card){
		try{
			ArrayList<Card> binderPart = this.cards.get(card.getName().charAt(0));
			for(int i = 0;i < binderPart.size();i++){
				if(binderPart.get(i).equals(card)){
					return binderPart.get(i);
				}
			}
		}catch(Exception e){}
		return null;
	}

	public boolean isStandAlone() {
		if(this.contains("Plains")
			&& this.contains("Island")
			&& this.contains("Swamp")
			&& this.contains("Mountain")
			&& this.contains("Forest")){
			return true;
		}
		return false;
	}
}
