package binder;

public class Deck {
	private Booster mainDeck;
	private Booster sideBoard;
	
	public Deck(){
		this.mainDeck = new Booster();
		this.sideBoard = new Booster();
	}
}
