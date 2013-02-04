package ihm.card;

import ihm.MainFrame;
import ihm.screen.Screen;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import binder.Deck;

@SuppressWarnings("serial")
public class CardDraggableDeck extends CardDecorator{

	Deck deck;
	public CardDraggableDeck(Deck deck,CardDrawable parent) {
		super(parent);
		this.deck = deck;
	}
	
	public void putMeUnderMouse(MouseEvent e){
		int x = (int) (e.getLocationOnScreen().getX() - MainFrame.getInstance().getScreen().getLocationOnScreen().getX());
		int y = (int) (e.getLocationOnScreen().getY() - MainFrame.getInstance().getScreen().getLocationOnScreen().getY());
		
		x = (int) (x - this.getSize().getWidth()/2);
		y = (int) (y - this.getSize().getHeight()/2);
		
		getTop().setLocation((int)x,(int)y);
	}
	
	String sourcePanel;
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		Screen screen = MainFrame.getInstance().getScreen();
		sourcePanel = screen.pointIsOnByScreen(e.getLocationOnScreen());
		putMeUnderMouse(e);
		screen.add(this,0);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		putMeUnderMouse(e);
		super.mouseDragged(e);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		Screen screen = MainFrame.getInstance().getScreen();
		String targetPanel = screen.pointIsOnByScreen(e.getLocationOnScreen());
		deck.transferCard(this.card, sourcePanel, targetPanel);
		screen.remove(this);
		screen.refresh();
	}
	
}
