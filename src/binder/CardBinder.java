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
	private Card getCard(Card card){
		try{
			ArrayList<Card> binderPart = this.cards.get(card.getName().charAt(0));
			for(int i = 0;i < binderPart.size();i++){
				if(binderPart.get(i).equals(card)){
					return binderPart.get(i);
				}
			}
		}catch(Exception e){
			return null;
		}
		return null;
		
	}
	public boolean contains(Card card) {
		if(getCard(card) == null) return false;
		else return true;
	}
	
	public int size(){
		Iterator<Character> it = cards.keySet().iterator();
		
		int size = 0;
		
		while(it.hasNext()){
			size += cards.get(it.next()).size();
		}
		
		return size;
	}
}
