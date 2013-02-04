package ihm.card;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import binder.Card;

@SuppressWarnings("serial")
public class CardDrawable extends JLabel implements MouseListener,MouseMotionListener{

	public static final int OVERVIEW_WIDTH = 100;
	public static final int OVERVIEW_HEIGHT = 142;
	
	public Card card;
	public ImageIcon image;
	
	public List<CardDrawable> decorators;

	public CardDrawable(Card card){
		this(card, -1,-1);
	}

	public CardDrawable(Card card, int w, int h) {
		this.decorators = new ArrayList<CardDrawable>();
		this.decorators.add(this);
		this.card = card;
		
		if(w>=0&&h>=0){
			this.image = getScaledImage(new ImageIcon(card.getImageLink()),w,h);
			this.setSize(w,h);
		}else{
			this.image = new ImageIcon(card.getImageLink());
			this.setSize(image.getIconWidth(), image.getIconHeight());
		}
		
		this.setIcon(image);
		this.setVisible(true);
	}


	private ImageIcon getScaledImage(ImageIcon srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg.getImage(), 0, 0, w, h, null);
	    g2.dispose();
	    return new ImageIcon(resizedImg);
	}

	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {}
	
	public CardDrawable getTop(){
		return this.decorators.get(this.decorators.size()-1);
	}

}
