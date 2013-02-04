package ihm.card;

import ia.Datawork;
import ihm.MainFrame;
import ihm.screen.IaImprovementScreen;

import java.awt.event.MouseEvent;

import binder.Card;

@SuppressWarnings("serial")
public class CardIaImprovement extends CardDecorator{

	String good,bad;
	public CardIaImprovement(Card otherCard,CardDrawable parent) {
		super(parent);
		this.good = parent.card.getReference();
		this.bad =  otherCard.getReference();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Datawork ia = new Datawork();
		ia.modifIa(this.good, this.bad);
		((IaImprovementScreen)MainFrame.getInstance().getScreen()).newChoice();
		super.mouseClicked(e);
	}

}