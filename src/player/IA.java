package player;

import binder.Card;

public class IA extends Player{
	@Override
	public Card getSelectedCard() {
		for(int i = 0;i < currentBooster.size();i++){
			if(currentBooster.get(i) != null)return currentBooster.get(i);
		}
		return null;
	}
}
