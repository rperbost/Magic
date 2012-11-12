package binder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SetBinder implements Binder{

	Map<String,CardBinder> binder;
	
	public SetBinder(){
		this.binder = new HashMap<String,CardBinder>();
	}
	
	public void addCard(Card card) {
		
		String set = card.getSet();
		if(!binder.containsKey(set)){
			binder.put(set, new CardBinder());
		}
		binder.get(set).addCard(card);

	}

	@Override
	public boolean contains(Card card) {
		String set = card.getSet();
		if(!binder.containsKey(set))return false;
		return binder.get(set).contains(card);
	}

	

	@Override
	public int size() {
		Iterator<String> it = binder.keySet().iterator();
		
		int size = 0;
		
		while(it.hasNext()){
			size += binder.get(it.next()).size();
		}
		
		return size;
	}
	
}
