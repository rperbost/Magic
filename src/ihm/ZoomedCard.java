package ihm;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import binder.Card;

@SuppressWarnings("serial")
public class ZoomedCard extends JLabel{
	private Card source;
	private boolean computedImage;
	
	public static final int WIDTH = 156;
	public static final int HEIGHT = 222;
	
	private static Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();
	    return resizedImg;
	}
	
	public ZoomedCard(Card source){
		this.source = source;
		this.computedImage = false;
		super.setVisible(false);
		
		
	}
	
	public void setVisible(boolean visibility){
		if(visibility){
			if(computedImage == false)this.computeImage();
			this.setBounds(0, 0, ZoomedCard.WIDTH, ZoomedCard.HEIGHT);
		}
		super.setVisible(visibility);
	}

	private void computeImage() {
		try{
			this.setIcon(new ImageIcon(
					ZoomedCard.getScaledImage(ImageIO.read(new File(source.getBigImageLink())), ZoomedCard.WIDTH, ZoomedCard.HEIGHT)
					));
			computedImage = true;
		}catch(Exception e){
		}
	}
	
}
