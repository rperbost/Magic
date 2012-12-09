package player;

import ihm.MainFrame;

public class Human extends Player{
	
	public void refresh(){
		MainFrame.getInstance().repaint();
	}

}
