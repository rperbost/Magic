package ihm.card;

import ihm.MainFrame;
import ihm.screen.*;

import java.awt.event.MouseEvent;
import java.rmi.RemoteException;


@SuppressWarnings("serial")
public class CardSelectDraftCard extends CardDecorator {

	public CardSelectDraftCard(CardDrawable parent) throws RemoteException {
		super(parent);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		try{
			((DraftScreen) MainFrame.getInstance().getScreen()).setSelectedCard(this.card);
		}catch(Exception err){};
	}

}
