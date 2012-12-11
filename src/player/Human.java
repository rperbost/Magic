package player;

import ihm.MainFrame;

public class Human extends Player{
	
	private String pseudo;
	
	public void refresh(){
		MainFrame.getInstance().repaint();
	}
	
	public Human (String pseudo){
		this.pseudo = pseudo;
	}

	public String getPseudo() {
		return this.pseudo;
	}
	
	

}
