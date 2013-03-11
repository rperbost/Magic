package binder;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;

import binder.interfaces.Booster;
import binder.interfaces.Card;

@SuppressWarnings("serial")
public class ImplementedBooster extends UnicastRemoteObject implements Booster {
	ArrayList<Card> booster;
	
	public ImplementedBooster() throws RemoteException {
		super();
		this.booster = new ArrayList<Card>();
	}

	public ImplementedBooster(ArrayList<Card> booster) throws RemoteException{
		super();
		this.booster = booster;
	}

	public void add(Card card) throws RemoteException{
		booster.add(card);
	}

	public Card get(int i) throws RemoteException{
		return booster.get(i);
	}

	public int indexOf(Card card) throws RemoteException{
		return this.booster.indexOf(card);
	}
	
	public void remove(Card selectedCard) throws RemoteException{
		for(Card c:booster){
			if(c.getReference().equals(selectedCard.getReference())){
				booster.remove(c);
				break;
			}
		}
				
	}
	
	public int size() throws RemoteException{
		return booster.size();
	}
	
	public void sortMe() throws RemoteException{
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
