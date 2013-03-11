package binder;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import binder.interfaces.Binder;
import binder.interfaces.Card;

@SuppressWarnings("serial")
public class SetBinder extends UnicastRemoteObject implements Binder{

	Map<String,CardBinder> binder;
	
	public SetBinder() throws RemoteException {
		super();
		this.binder = new HashMap<String,CardBinder>();
	}

	@Override
	public void addCard(Card card) throws RemoteException {
		String set = card.getSet();
		if(!binder.containsKey(set)){
			binder.put(set, new CardBinder());
		}
		binder.get(set).addCard(card);
	}

	@Override
	public boolean contains(Card card) throws RemoteException {
		String set = card.getSet();
		if(!binder.containsKey(set))return false;
		return binder.get(set).contains(card);
	}

	@Override
	public boolean contains(String name) throws RemoteException {
		if(get(name)==null)return false;
		else return true;
	}

	@Override
	public Card get(int i) throws RemoteException {
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

	@Override
	public Card get(String name) throws RemoteException {
		Iterator<String> it = binder.keySet().iterator();
		
		while(it.hasNext()){
			String s = it.next();
			
			if(binder.get(s).contains(name)){
				return binder.get(s).get(name);
			}
		}
	
		return null;
	}

	@Override
	public Card get(String set, String id) throws RemoteException {
		return getByRef(ref+id);
	}

	@Override
	public Card getByRef(String ref) throws RemoteException {
		try{
			return binder.get(ref.substring(0,3)).getByRef(ref);
		}catch(Exception e){}
		return null;
	}

	@Override
	public Binder getSet(String set) throws RemoteException {
		if(binder.containsKey(set))return binder.get(set);
		else return new CardBinder();
	}

	@Override
	public List<String> getSetList() throws RemoteException {
		List<String> sets = new ArrayList<String>();
		Iterator<String> it = binder.keySet().iterator();
		
		while(it.hasNext()){
			String set = it.next();
			if(!sets.contains(set))sets.add(set);
		}
		return sets;
	}

	@Override
	public int size() throws RemoteException {
		Iterator<String> it = binder.keySet().iterator();
		
		int size = 0;
		
		while(it.hasNext()){
			size += binder.get(it.next()).size();
		}
		
		return size;
	}

}
