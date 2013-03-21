package ihm.card;

import ihm.MainFrame;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import binder.ICard;

@SuppressWarnings("serial")
public class CardDrawable extends JLabel implements MouseListener,MouseMotionListener{

	private static Map<ICard,ImageIcon>IMAGE_CACHE = new HashMap<ICard,ImageIcon>();
	public static final int OVERVIEW_HEIGHT = 142;
	
	public static final int OVERVIEW_WIDTH = 100;
	public ICard card;
	
	public List<CardDrawable> decorators;
	
	public ImageIcon image;

	public CardDrawable(ICard card) throws RemoteException{
		this(card, -1,-1);
	}

	public CardDrawable(ICard card, int w, int h) throws RemoteException {
		this.decorators = new ArrayList<CardDrawable>();
		this.decorators.add(this);
		this.card = card;
		

		try {
			if(!IMAGE_CACHE.containsKey(card)){
				String urlString = "http://www.magic-ville.com/pics/big/"+card.getSet()+"/"+card.getIndex()+".jpg";
				URL url = new URL(urlString);
				ImageIcon imageIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(url));
				IMAGE_CACHE.put(card, imageIcon);
			}
			if(w>=0&&h>=0){
				this.image = getScaledImage(IMAGE_CACHE.get(card),w,h);
				this.setSize(w,h);
			}else{
				this.image = IMAGE_CACHE.get(card);
				this.setSize(image.getIconWidth(), image.getIconHeight());
			}
			
			this.setIcon(image);
			
			MainFrame.getInstance().getScreen().repaint();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		
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

	public CardDrawable getTop(){
		return this.decorators.get(this.decorators.size()-1);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {}
	
	@Override
	public void mousePressed(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	


}
