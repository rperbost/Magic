package ihm;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import binder.Card;

@SuppressWarnings("serial")
public abstract class DrawableCard  extends JLabel implements MouseListener,MouseMotionListener{
	
	static final int CARD_WIDTH = 100;
	static final int CARD_HEIGHT = 142;
	
	protected ZoomedCard zoomedCard;
	
	protected JPanel parent;
	
	protected Card card;
	
	public DrawableCard(Card card, JPanel parent){
		this.setIcon(new ImageIcon(card.getLilImageLink()));
		
		this.card = card;
		
		this.setSize(CARD_WIDTH, CARD_HEIGHT);
		
		this.parent = parent;
		
		zoomedCard = new ZoomedCard(card);	
		parent.add(zoomedCard);
		
		parent.setComponentZOrder(zoomedCard, 0);
		
		this.setBounds(0, 0, CARD_WIDTH, CARD_HEIGHT);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		this.setVisible(true);
	}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getPoint().x +this.getX() +1;
		int y = e.getPoint().y +this.getY() +1;
		
		zoomedCard.setLocation(x,y);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		zoomedCard.setVisible(true);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		zoomedCard.setVisible(false);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
