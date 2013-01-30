package ihm.card;

import ia.Datawork;
import ihm.screen.IaImprovementScreen;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import binder.Card;

public class IaImprovementCard  extends JLabel implements MouseListener{

	Card card;
	Card otherCard;
	Datawork ia;
	IaImprovementScreen iaImprovementScreen;
	
	public IaImprovementCard(Card card, Card otherCard, Datawork ia, IaImprovementScreen iaImprovementScreen) {
		this.setBounds(0, 0, 400, 600);
		this.setIcon(new ImageIcon(card.getBigImageLink()));
		this.card = card;
		this.otherCard = otherCard;
		this.ia = ia;
		this.setHorizontalAlignment(CENTER);
		this.iaImprovementScreen = iaImprovementScreen;
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		ia.modifIa(card.getReference(), otherCard.getReference());
		iaImprovementScreen.newChoice();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
