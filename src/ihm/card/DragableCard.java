package ihm.card;

import ihm.screen.DeckScreen;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import binder.Card;

public class DragableCard extends DrawableCard implements MouseInputListener{
	
	DeckScreen screen;
	public DragableCard(Card card, JPanel parent) {
		super(card, parent);
		screen = (DeckScreen)this.parent;
	}
	
	String sourcePanel;
	public void mousePressed(MouseEvent arg0) {
		super.mousePressed(arg0);
		sourcePanel = ((JPanel) arg0.getComponent().getParent()).getName();
	}
	
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
		
		this.setVisible(true);
		
		int x = (int) (e.getLocationOnScreen().getX() - screen.getLocationOnScreen().getX());
		int y = (int) (e.getLocationOnScreen().getY() - screen.getLocationOnScreen().getY());
		
		this.setLocation(x-DrawableCard.CARD_WIDTH/2,y-CARD_HEIGHT/2);
		screen.add(this,0);
	}

	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		
		String targetPanel = screen.pointIsOnByScreen(e.getLocationOnScreen());
		
		screen.getDeck().transferCard(this.card, sourcePanel, targetPanel);
		
		screen.remove(this);
		screen.refreshDeck();
		screen.refreshBottom();
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
