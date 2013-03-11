package ihm.card;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

@SuppressWarnings("serial")
public class CardBumpOnHover extends CardDecorator {

	int pas;
	
	public CardBumpOnHover(CardDrawable parent) throws RemoteException {
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
