package ihm.card;

import ihm.MainFrame;
import ihm.screen.Screen;

import java.awt.Cursor;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

public abstract class CardZoom extends CardDecorator{

	public static final int ZOOM_WIDTH = 156;
	public static final int ZOOM_HEIGHT = 222;
	
	CardDrawable zoom;
	
	public CardZoom(CardDrawable parent) {
		super(parent);
		zoom = new CardDrawable(parent.card, ZOOM_WIDTH,ZOOM_HEIGHT);
	}

	MainFrame m = MainFrame.getInstance();
	public void mouseEntered(MouseEvent e) {
		super.mouseEntered(e);
		Screen s = m .getScreen();
		s.add(zoom);
		s.setComponentZOrder(zoom, 0);
		s.repaint();
	}
	
	public void mouseExited(MouseEvent e) {
		super.mouseExited(e);
		removeZoom();
	}

	private void removeZoom() {
		Screen s = m .getScreen();
		s.remove(zoom);
		s.repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		removeZoom();
		super.mouseDragged(e);
	}
}
