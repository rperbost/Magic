package ihm;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import binder.Card;

import player.Player;

public class ClickableCard extends JLabel{

	static int cardWidth = 100;
	static int cardHeight = 142;
	static int boxWidth = cardWidth+15;
	static int boxHeight = cardHeight+15;
	
	private Player player;
	int cardId;
	public ClickableCard(Player p,int cardId){
		super(new ImageIcon(p.getCard(cardId).getLilImageLink()));
		
		
		int col = cardId%5;
		int row = cardId/5;
		
		this.setBounds(col*boxWidth, row*boxHeight, cardWidth, cardHeight);
		
		final ClickableCard me = this;
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				me.player.setSelectedCard(me.cardId);
				System.out.println(me.cardId);
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			
			
		});
		this.player = p;
		this.cardId = cardId;
		this.setVisible(true);
		this.setSize(100, 142);
	}

}
