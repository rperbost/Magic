package ihm.card;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.rmi.RemoteException;

@SuppressWarnings("serial")
public class CardDecorator extends CardDrawable implements MouseListener,MouseMotionListener{

	CardDrawable parent;
	
	public CardDecorator(CardDrawable parent) throws RemoteException {
		super(parent.card);
		this.parent = parent;
		this.setLocation(parent.getLocation());
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setSize(parent.getSize());
		this.decorators = parent.decorators;
		this.decorators.add(this);
	}

	@Override
	public void addMouseListener(MouseListener l) {
		parent.addMouseListener(l);
		super.addMouseListener(l);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		parent.mouseClicked(e);
		super.mouseClicked(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		parent.mouseDragged(e);
		super.mouseDragged(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		parent.mouseEntered(e);
		super.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		parent.mouseExited(e);
		super.mouseExited(e);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		parent.mouseMoved(e);
		super.mouseMoved(e);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		parent.mousePressed(e);
		super.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		parent.mouseReleased(e);
		super.mouseReleased(e);
	}

	public void paint(Graphics g) {
		parent.paint(g);
	}
}
