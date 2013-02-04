package ihm.card;

import ihm.MainFrame;

import java.awt.Cursor;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class CardHandOnHover extends CardDecorator{

	public CardHandOnHover(CardDrawable parent) {
		super(parent);
	}
	
	public void mouseEntered(MouseEvent e) {
		super.mouseEntered(e);
		MainFrame.getInstance().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	public void mouseExited(MouseEvent e) {
		super.mouseExited(e);
		MainFrame.getInstance().setCursor(Cursor.getDefaultCursor());
	}
}
