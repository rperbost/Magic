package ihm.card;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import binder.Card;

public class LandCard extends DragableCard {

	public LandCard(Card card, JPanel parent) {
		super(card, parent);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		super.mouseEntered(arg0);
		this.setLocation(this.getLocation().x, this.getLocation().y-5);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		super.mouseExited(arg0);
		this.setLocation(this.getLocation().x, this.getLocation().y+5);
	}
}
