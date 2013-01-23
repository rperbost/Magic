package binder;

public class Deck {
	private Booster mainDeck;
	private Booster sideboard;
	
	public Booster getMainDeck() {
		return mainDeck;
	}

	public Booster getSideboard() {
		return sideboard;
	}

	public Deck(){
		this.mainDeck = new Booster();
		this.sideboard = new Booster();
	}
	
	public void addToSideboard(Card card){
		sideboard.add(card);
		sideboard.sortMe();
	}
	public void addToDeck(Card card){
		mainDeck.add(card);
		mainDeck.sortMe();
	}
	public void removeFromSideboard(Card card){
		sideboard.remove(card);
	}
	public void removeFromDeck(Card card){
		mainDeck.remove(card);
	}

	public void transferCard(Card card, String source, String target) {
				
		boolean cardMoved = false;
		if(target.equals("SIDEBOARD")){
			addToSideboard(card);
			cardMoved=true;
		}
		if(target.equals("DECK")){
			addToDeck(card);
			cardMoved=true;
		}
		if(target.equals("TRASH")){
			cardMoved=true;
		}
		
		if(source.equals("SIDEBOARD")&&cardMoved){
			removeFromSideboard(card);
		}
		if(source.equals("DECK")&&cardMoved){
			removeFromDeck(card);
		}
		
	}
}
