package ihm.card;

import ihm.MainFrame;
import ihm.screen.IScreen;

import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

@SuppressWarnings("serial")
public abstract class CardZoom extends CardDecorator{

	public static final int ZOOM_HEIGHT = 222;
	public static final int ZOOM_WIDTH = 156;
	
	MainFrame m = MainFrame.getInstance();
	
	CardDrawable zoom;

	public CardZoom(CardDrawable parent) throws RemoteException {
		super(parent);
		zoom = new CardDrawable(parent.card, ZOOM_WIDTH,ZOOM_HEIGHT);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		removeZoom();
		super.mouseDragged(e);
	}
	
	public void mouseEntered(MouseEvent e) {
		super.mouseEntered(e);
		IScreen s = m .getScreen();
		s.add(zoom);
		s.setComponentZOrder(zoom, 0);
		s.repaint();
	}

	public void mouseExited(MouseEvent e) {
		super.mouseExited(e);
		removeZoom();
	}
	
	private void removeZoom() {
		IScreen s = m .getScreen();
		s.remove(zoom);
		s.repaint();
	}
}
