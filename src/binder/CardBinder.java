package binder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class CardBinder implements Binder{
	Map< Character , Vector<Card> > cards;
	
	public CardBinder(){
		cards = new HashMap < Character, Vector<Card> >();
	}
	
	public void addCard(Card card){
		String cardName = card.getName();
		
		if(cardName.length() == 0)return;
		
		Character c = new Character(card.getName().charAt(0));
		
		Vector<Card> binderPart = null;
	
		if(!this.contains(card)){
			if(!this.cards.containsKey(c)){
				this.cards.put(c, new Vector<Card>());
			}
			binderPart = this.cards.get(c);
			binderPart.add(card.clone());
		}
	}
	private Card getCard(Card card){
		try{
			Vector<Card> binderPart = this.cards.get(card.getName().charAt(0));
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
