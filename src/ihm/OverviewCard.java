package ihm;

import javax.swing.JPanel;

import binder.Booster;

@SuppressWarnings("serial")
public class OverviewCard extends DrawableCard {
	
	public OverviewCard(Booster b, int cardPosition, JPanel parent) {
		super(b.get(cardPosition), parent);
		int x = (CARD_WIDTH+2)*(cardPosition%2)+1;
		int y = 15+7*cardPosition;
		if(cardPosition%2==1)y-=7;
		this.setLocation(x,y);
	}

}
