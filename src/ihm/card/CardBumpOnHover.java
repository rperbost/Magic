package ihm.card;

import ihm.MainFrame;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class CardBumpOnHover extends CardDecorator {

	int pas;
	
	public CardBumpOnHover(CardDrawable parent) {
		super(parent);
		this.pas = 10;
	}
	
	public void mouseEntered(MouseEvent e) {
		super.mouseEntered(e);
		Point p = getTop().getLocation();
		getTop().setLocation(p.x,p.y-this.pas);
	}
	
	public void mouseExited(MouseEvent e) {
		super.mouseExited(e);
		Point p = getTop().getLocation();
		getTop().setLocation(p.x,p.y+this.pas);
	}
}
