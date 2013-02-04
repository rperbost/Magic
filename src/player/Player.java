package player;

import server.ServerThread;
import binder.Booster;
import binder.Card;
import binder.Deck;

public abstract class Player {
	protected Booster currentBooster;
	protected Deck currentDeck;
	protected Card selectedCard;
	protected int chair;
	protected ServerThread callback;
	
	public Player(){
		currentBooster = new Booster();
		currentDeck = new Deck();
		selectedCard = null;
		chair = -1;
	}
	
	public void setCallback(ServerThread callback){
		this.callback = callback;
	}
	public void setChair(int chairNumber){
		chair = chairNumber;
	}
	public int getChair(){
		return chair;
	}
	
	
	public void setBooster(Booster booster){
		currentBooster = booster;
		selectedCard = null;
	}
	
	public void setDeck(Deck deck){
		currentDeck = deck;
	}
	
	public Card getSelectedCard(){
		return selectedCard;
	}

	public void setRandomCard() {
		selectedCard = currentBooster.get((int)Math.random()*currentBooster.size());
	}
	
	public void setSelectedCard(int i) {
		try{
			selectedCard = currentBooster.get(i);
		}catch(NullPointerException e){
			setRandomCard();
		}
		callback.notifyReady(this);
	}
	public void setSelectedCard(Card card) {
		 setSelectedCard(currentBooster.indexOf(card));
	}
	
	public int getTimer(){
		if(callback != null)return callback.getTimer();
		return 0;
	}

	public Card getCard(int cardId) {
		return currentBooster.get(cardId);
	}

	public Booster getBooster() {
		return this.currentBooster;
	}

	public void refresh() {
	}

	public int getSelectedCardId() {
		if(selectedCard == null) return -1;
		return currentBooster.indexOf(selectedCard);
	}

	public Deck getDeck() {
		return this.currentDeck;
	}
	
	public abstract String getPseudo();
	
	public boolean[] getReadyStates(){
		try{
			return callback.getReadys();
		}catch(NullPointerException e){
			return null;
		}
	}
	
	public String[] getPseudos(){
		return callback.getPseudos();
	}
	
	public void startDeckList(){
		
	}

	
}
