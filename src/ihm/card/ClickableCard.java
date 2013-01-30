package ihm.card;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import player.Player;

@SuppressWarnings("serial")
public class ClickableCard extends DrawableCard{

	private int boxWidth = CARD_WIDTH+15;
	private int boxHeight = CARD_HEIGHT+15;
	
	private Player player;
	int cardId;
	
	public ClickableCard(Player p,int cardId, JPanel parent){
		super(p.getCard(cardId),parent);
		
		int col = cardId%5;
		int row = cardId/5;
		
		int x = col*boxWidth+15;
		int y = row*boxHeight+45;
		
		this.setLocation(x, y);
		
		this.player = p;
		this.cardId = cardId;		
	}
	
	public void mouseEntered(MouseEvent e) {
		super.mouseEntered(e);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	public void mouseExited(MouseEvent e) {
		super.mouseExited(e);
		setCursor(Cursor.getDefaultCursor());
	}

	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		this.player.setSelectedCard(this.cardId);
	}
	public void mouseMoved(MouseEvent e) {
		int x = e.getPoint().x +this.getX() +1;
		int y = e.getPoint().y +this.getY() +1;

		if(x > 300){
			x -=  ZoomedCard.WIDTH -2;
		}
		if(y > 273){
			y -=  ZoomedCard.HEIGHT -2;
		}
		
		zoomedCard.setLocation(x,y);
	}
}

