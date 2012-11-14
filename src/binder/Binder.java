package binder;

import java.util.ArrayList;

public interface Binder {
	public boolean contains(Card card);
	public void addCard(Card card);
	public int size();
	
	public ArrayList<Card> getBooster();
}
