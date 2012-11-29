package player;

import server.ServerThread;
import binder.Booster;
import binder.Card;

public abstract class Player {
	protected Booster currentBooster;
	protected Card selectedCard;
	protected int chair;
	protected ServerThread callback;
	
	public Player(){
		currentBooster = null;
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
	
	public int getTimer(){
		if(callback != null)return callback.getTimer();
		return 0;
	}

	public Card getCard(int cardId) {
		return currentBooster.get(cardId);
	}

	public Booster getBooster() {
		return currentBooster;
	}
}
