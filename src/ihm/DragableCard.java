package ihm;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import binder.Card;

public class DragableCard extends DrawableCard implements MouseInputListener{
	
	public DragableCard(Card card, JPanel parent) {
		super(card, parent);
	}
	
	String sourcePanel;
	public void mousePressed(MouseEvent arg0) {
		super.mousePressed(arg0);
		sourcePanel = ((JPanel) arg0.getComponent().getParent()).getName();
	}
	
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
		//System.out.println("dragged : " + this.card);
	}

	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		
		DeckScreen screen = (DeckScreen)this.parent;
		
		String targetPanel = screen.pointIsOnByScreen(e.getLocationOnScreen());
		//String originPanel = e.getX()+"";
		screen.getDeck().transferCard(this.card, sourcePanel, targetPanel);
		
		System.out.println("drop : " + this.card+ " "+targetPanel+ " source "+ sourcePanel);
		
		screen.refreshDeck();
	}
	
	public void mouseEntered(MouseEvent arg0) {
		DeckScreen screen = (DeckScreen)this.parent;
		screen.setZoom(this.zoomedCard);
	}

	public void mouseExited(MouseEvent arg0) {
		
	}
	
	public void mouseMoved(MouseEvent e){
		
	}
}
