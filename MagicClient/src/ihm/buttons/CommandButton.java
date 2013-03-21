package ihm.buttons;

import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import javax.swing.JButton;

import rmi.MagicRemoteManager;


@SuppressWarnings("serial")
public class CommandButton extends JButton implements ICommand{

	MagicRemoteManager server;
	public CommandButton(String label) throws RemoteException{
		super(label);
		server = MagicRemoteManager.getInstance();
		this.addMouseListener(this);
	}
	
	@Override
	public void execute() throws RemoteException{}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		try{
			this.execute();
		}catch(RemoteException e){
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
