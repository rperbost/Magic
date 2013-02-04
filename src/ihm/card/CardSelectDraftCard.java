package ihm.card;

import ihm.MainFrame;
import ihm.screen.*;

import java.awt.event.MouseEvent;


@SuppressWarnings("serial")
public class CardSelectDraftCard extends CardDecorator {

	public CardSelectDraftCard(CardDrawable parent) {
		super(parent);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		try{
			((DraftScreen) MainFrame.getInstance().getScreen()).getPlayer().setSelectedCard(this.card);
		}catch(Exception err){};
	}

}
