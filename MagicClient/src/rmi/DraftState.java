package rmi;

import java.rmi.RemoteException;


import binder.ImplementedBooster;
import binder.ImplementedDeck;
import binder.interfaces.Binder;
import binder.interfaces.Booster;
import binder.interfaces.Deck;

public class DraftState {
	String splitedState[];
	private String state;

	public DraftState(String state){
		this.state = state;
		
		this.splitedState = state.split("\t");
	}
	
	public boolean equals(String state){
		return this.state.equals(state);
	}
	
	public Booster getBooster() throws RemoteException{
		String[] cards = splitedState[4].split("&");
		Booster b = new ImplementedBooster();
		Binder binder = MagicRemoteManager.getInstance().getBinder();
		for(String ref:cards){
			if(binder.getByRef(ref)!=null)
				b.add(binder.getByRef(ref));
		}
		return b;
	}
	
	public Deck getDeck() throws RemoteException{
		String[] cards = splitedState[3].split("&");
		Deck d = new ImplementedDeck();
		Binder binder = MagicRemoteManager.getInstance().getBinder();
		for(String ref:cards){
			if(binder.getByRef(ref)!=null)
				d.addToDeck(binder.getByRef(ref));
		}
		return d;
	}
	
	public String[] getPseudos(){
		return (splitedState[0]).split("&");
	}
	
	public boolean[] getReadyStates(){
		boolean retour[] = new boolean[8];
		
		String r[] = (splitedState[1]).split("&");

		for(int i = 0; i < 8; i++){
			retour[i]=Boolean.parseBoolean(r[i]);
		}
		
		return retour;
	}
	public int getSelectedCardId() throws RemoteException{
		return Integer.parseInt(splitedState[2]);
	}

	public String getTimerString() {
		while(splitedState[5].length()<5)splitedState[5]="0"+splitedState[5];
		return splitedState[5].substring(0,2);
	}
}
