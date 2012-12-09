package ihm;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import player.Player;

@SuppressWarnings("serial")
public class ClickableCard extends JLabel{

	static int cardWidth = 100;
	static int cardHeight = 142;
	static int boxWidth = cardWidth+15;
	static int boxHeight = cardHeight+15;
	
	private Player player;
	int cardId;
	private ZoomedCard zoomedCard;	
	
	public ClickableCard(Player p,int cardId, JPanel parent){
		super(new ImageIcon(p.getCard(cardId).getLilImageLink()));
		
		
		int col = cardId%5;
		int row = cardId/5;
		
		zoomedCard = new ZoomedCard(p.getCard(cardId));	
		parent.add(zoomedCard);
		
		parent.setComponentZOrder(zoomedCard, 0);
		
		int x = col*boxWidth+15;
		int y = row*boxHeight+45;
		
		this.setBounds(x, y, cardWidth, cardHeight);
		
		final ClickableCard me = this;
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				me.player.setSelectedCard(me.cardId);
				System.out.println(me.cardId);
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				zoomedCard.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
				zoomedCard.setVisible(false);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
				
			}

			
			
		});
		
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				int x = e.getPoint().x +me.getX() +1;
				int y = e.getPoint().y +me.getY() +1;

				if(x > 300){
					x -=  ZoomedCard.WIDTH -2;
				}
				if(y > 273){
					y -=  ZoomedCard.HEIGHT -2;
				}
				
				zoomedCard.setLocation(x,y);
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		this.player = p;
		this.cardId = cardId;
		this.setVisible(true);
		this.setSize(100, 142);
	}

}
