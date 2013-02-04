package ihm.card;

import ihm.MainFrame;
import ihm.screen.Screen;

import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class CardZoomUnderMouse extends CardZoom{

	public CardZoomUnderMouse(CardDrawable parent) {
		super(parent);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		super.mouseMoved(e);
		int x = (int) (e.getLocationOnScreen().getX() - MainFrame.getInstance().getScreen().getLocationOnScreen().getX());
		int y = (int) (e.getLocationOnScreen().getY() - MainFrame.getInstance().getScreen().getLocationOnScreen().getY());
		
		Screen s = m.getScreen();
		if(x > s.getWidth()/2)x-=ZOOM_WIDTH;
		if(y > s.getHeight()/2)y-=ZOOM_HEIGHT;
		
		zoom.setLocation(x,y);
		
		s.repaint();
	}
	
}
