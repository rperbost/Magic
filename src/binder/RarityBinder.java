package binder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import binder.Card.Rarity;

public class RarityBinder implements Binder{

	Map<Card.Rarity,CardBinder> binder;
	
	public RarityBinder(){
		this.binder = new HashMap<Card.Rarity,CardBinder>();
	}
	
	public void addCard(Card card) {
		
		Card.Rarity rarity = card.getRarity();
		if(!binder.containsKey(rarity)){
			binder.put(rarity, new CardBinder());
		}
		binder.get(rarity).addCard(card);

	}

	@Override
	public boolean contains(Card card) {
		Card.Rarity rarity = card.getRarity();
		if(!binder.containsKey(rarity))return false;
		return binder.get(rarity).contains(card);
	}

	

	@Override
	public int size() {
		Iterator<Card.Rarity> it = binder.keySet().iterator();
		
		int size = 0;
		
		while(it.hasNext()){
			size += binder.get(it.next()).size();
		}
		
		return size;
	}
	
}
