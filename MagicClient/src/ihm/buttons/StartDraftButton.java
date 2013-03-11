package ihm.buttons;

import java.rmi.RemoteException;

@SuppressWarnings("serial")
public class StartDraftButton extends CommandButton{
	public StartDraftButton() throws RemoteException{
		super("Commencer le draft");
	}
	@Override
	public void execute() throws RemoteException {
		server.startDraft();
	}
}
