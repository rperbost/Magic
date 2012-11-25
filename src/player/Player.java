package player;

import binder.Booster;
import binder.Card;

public class Player {
	Booster currentBooster;
	Card selectedCard;
	
	public Player(){
		currentBooster = null;
		selectedCard = null;
	}
	
	public void setBooster(Booster booster){
		currentBooster = booster;
		selectedCard = null;
	}
	
	public Card getSelectedCard(){
		return selectedCard;
	}
}
