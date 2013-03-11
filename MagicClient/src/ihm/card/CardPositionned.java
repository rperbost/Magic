package ihm.card;

import java.rmi.RemoteException;

@SuppressWarnings("serial")
public class CardPositionned extends CardDecorator{

	public CardPositionned(int x,int y,CardDrawable parent) throws RemoteException {
		super(parent);
		super.setLocation(x, y);
	}

}
