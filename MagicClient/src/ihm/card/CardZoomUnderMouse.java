package ihm.card;

import ihm.MainFrame;
import ihm.screen.IScreen;

import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

@SuppressWarnings("serial")
public class CardZoomUnderMouse extends CardZoom{

	public CardZoomUnderMouse(CardDrawable parent) throws RemoteException {
		super(parent);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		super.mouseMoved(e);
		int x = (int) (e.getLocationOnScreen().getX() - MainFrame.getInstance().getScreen().getLocationOnScreen().getX());
		int y = (int) (e.getLocationOnScreen().getY() - MainFrame.getInstance().getScreen().getLocationOnScreen().getY());
		
		IScreen s = m.getScreen();
		if(x > s.getWidth()/2)x-=ZOOM_WIDTH;
		if(y > s.getHeight()/2)y-=ZOOM_HEIGHT;
		
		zoom.setLocation(x,y);
		
		s.repaint();
	}
	
}
