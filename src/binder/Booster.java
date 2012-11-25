package binder;

import java.util.ArrayList;

public class Booster {
	ArrayList<Card> booster;
	
	public Booster(ArrayList<Card> booster){
		this.booster = booster;
	}

	public void remove(Card selectedCard) {
		booster.remove(selectedCard);		
	}

	public int size() {
		return booster.size();
	}

	public Card get(int i) {
		return booster.get(i);
	}
	
	
}
