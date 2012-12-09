package ihm;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import player.Player;

@SuppressWarnings("serial")
public class SelectedClickableCard extends ClickableCard{

	public SelectedClickableCard(Player p, int cardId, JPanel parent) {
		super(p, cardId, parent);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.GREEN);
		for(int i = 0; i < 4;i++){
			g.drawRect(i, i, this.getWidth()-i*2, this.getHeight()-i*2);
		}
	}
}
