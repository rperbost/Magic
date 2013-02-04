package ihm.card;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class CardSelected extends CardDecorator {
	
	public CardSelected(CardDrawable parent) {
		super(parent);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.GREEN);
		for(int i = 0; i < 4;i++){
			g.drawRect(i, i, this.getWidth()-i*2, this.getHeight()-i*2);
		}
	}
}
