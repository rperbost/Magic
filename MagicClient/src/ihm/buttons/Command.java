package ihm.buttons;

import java.awt.event.MouseListener;
import java.rmi.RemoteException;

public interface Command extends MouseListener{
	public void execute() throws RemoteException;
}
