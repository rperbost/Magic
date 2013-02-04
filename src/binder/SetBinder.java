package binder;

import java.util.ArrayList;
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

	public boolean contains(String name) {
		if(get(name)==null)return false;
		else return true;
	}

	public Card get(String set,int i){
		return binder.get(set).get(i);
	}

	public Card get(int i){
		Iterator<String> it = binder.keySet().iterator();
				
		while(it.hasNext()){
			String s = it.next();
			int size = binder.get(s).size();
			if(i >= size){
				i-=size;
			}else{
				return binder.get(s).get(i);
			}
		}
	
		return null;	
	}

	public Card get(String name) {
		Iterator<String> it = binder.keySet().iterator();
		
		while(it.hasNext()){
			String s = it.next();
			
			if(binder.get(s).contains(name)){
				return binder.get(s).get(name);
			}
		}
	
		return null;
	}

	public Card get(String ref, String id) {
		return getByRef(ref+id);
	}

	public Card getByRef(String ref){
		try{
			return binder.get(ref.substring(0,3)).getByRef(ref);
		}catch(Exception e){}
		return null;
	}

	//forwarders
	public int size(String set){
		return binder.get(set).size();
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
	
	public ArrayList<String> getSets(){
		return new ArrayList<String>(binder.keySet());
	}

	public CardBinder getSet(String set){
		return binder.get(set);
	}
}
