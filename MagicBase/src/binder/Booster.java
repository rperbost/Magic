package binder;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;


@SuppressWarnings("serial")
public class Booster extends UnicastRemoteObject implements IBooster {
	ArrayList<ICard> booster;
	
	public Booster() throws RemoteException {
		super();
		this.booster = new ArrayList<ICard>();
	}

	public Booster(ArrayList<ICard> booster) throws RemoteException{
		super();
		this.booster = booster;
	}

	public void add(ICard card) throws RemoteException{
		booster.add(card);
	}

	public ICard get(int i) throws RemoteException{
		return booster.get(i);
	}

	public int indexOf(ICard card) throws RemoteException{
		return this.booster.indexOf(card);
	}
	
	public void remove(ICard selectedCard) throws RemoteException{
		for(ICard c:booster){
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
