package ihm.card;

import ihm.MainFrame;
import ihm.screen.Screen;

import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import binder.interfaces.Deck;

@SuppressWarnings("serial")
public class CardDraggableDeck extends CardDecorator{

	Deck deck;
	String sourcePanel;
	
	public CardDraggableDeck(Deck deck,CardDrawable parent) throws RemoteException {
		super(parent);
		this.deck = deck;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		putMeUnderMouse(e);
		super.mouseDragged(e);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		Screen screen = MainFrame.getInstance().getScreen();
		sourcePanel = screen.pointIsOnByScreen(e.getLocationOnScreen());
		putMeUnderMouse(e);
		screen.add(this,0);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		Screen screen = MainFrame.getInstance().getScreen();
		String targetPanel = screen.pointIsOnByScreen(e.getLocationOnScreen());
		try {
			deck.transferCard(this.card, sourcePanel, targetPanel);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		screen.remove(this);
		try {
			screen.refresh();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}
	
	public void putMeUnderMouse(MouseEvent e){
		int x = (int) (e.getLocationOnScreen().getX() - MainFrame.getInstance().getScreen().getLocationOnScreen().getX());
		int y = (int) (e.getLocationOnScreen().getY() - MainFrame.getInstance().getScreen().getLocationOnScreen().getY());
		
		x = (int) (x - this.getSize().getWidth()/2);
		y = (int) (y - this.getSize().getHeight()/2);
		
		getTop().setLocation((int)x,(int)y);
	}
	
}
