package binder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import binder.Card.Rarity;

public class RarityBinder implements Binder{

	Map<Rarity,CardBinder> binder;
	
	public RarityBinder(){
		this.binder = new HashMap<Rarity,CardBinder>();
	}
	
	public void addCard(Card card) {
		
		Rarity rarity = card.getRarity();
		if(!binder.containsKey(rarity)){
			binder.put(rarity, new CardBinder());
		}
		binder.get(rarity).addCard(card);

	}

	@Override
	public boolean contains(Card card) {
		Rarity rarity = card.getRarity();
		if(!binder.containsKey(rarity))return false;
		return binder.get(rarity).contains(card);
	}

	

	@Override
	public int size() {
		Iterator<Rarity> it = binder.keySet().iterator();
		
		int size = 0;
		
		while(it.hasNext()){
			size += binder.get(it.next()).size();
		}
		
		return size;
	}
	
	public Card get(int i){
		Iterator<Rarity> it = binder.keySet().iterator();
				
		while(it.hasNext()){
			Rarity r = it.next();
			int size = binder.get(r).size();
			if(i >= size){
				i-=size;
			}else{
				return binder.get(r).get(i);
			}
		}

		return null;	
	}
}
