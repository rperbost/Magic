package player;

import ihm.MainFrame;
import ihm.screen.DeckScreen;
import ihm.screen.IaImprovementScreen;

public class Human extends Player{
	
	private String pseudo;
	
	public void refresh(){
		MainFrame.getInstance().repaint();
	}
	
	public void startDeckList(){
		MainFrame
		.getInstance()
		.addScreen("DECK", new DeckScreen("13m",this.currentDeck))
		.addScreen("IA", new IaImprovementScreen())
		.activeScreen("IA");
	}
	
	public Human (String pseudo){
		this.pseudo = pseudo;
	}

	public String getPseudo() {
		return this.pseudo;
	}
	
	

}
