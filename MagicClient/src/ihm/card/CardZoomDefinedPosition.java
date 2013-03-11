package ihm.card;

import java.rmi.RemoteException;

@SuppressWarnings("serial")
public class CardZoomDefinedPosition extends CardZoom{

	public CardZoomDefinedPosition(int x, int y,CardDrawable parent) throws RemoteException {
		super(parent);
		zoom.setLocation(x,y);
	}
	
}
