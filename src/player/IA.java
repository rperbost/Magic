package player;

import binder.Booster;

public class IA extends Player{
	@Override
	public void setBooster(Booster booster) {
		super.setBooster(booster);
		super.setRandomCard();
		callback.notifyReady(this);
	}
	
	public String getPseudo() {
		return "bot";
	}
}
