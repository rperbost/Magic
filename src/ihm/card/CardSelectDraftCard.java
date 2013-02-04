package ihm.card;

import ihm.MainFrame;
import ihm.screen.DeckScreen;
import ihm.screen.DraftScreen;

import java.awt.event.MouseEvent;


public class CardSelectDraftCard extends CardDecorator {

	public CardSelectDraftCard(CardDrawable parent) {
		super(parent);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		try{
			((DraftScreen) MainFrame.getInstance().getScreen()).getPlayer().setSelectedCard(this.card);
			System.out.println(this.card.getName());
		}catch(Exception err){};
	}

}
