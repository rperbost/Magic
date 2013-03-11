package ihm.buttons;

import java.rmi.RemoteException;

@SuppressWarnings("serial")
public class DeleteRoomButton extends CommandButton{
	int roomId;
	public DeleteRoomButton(int id) throws RemoteException{
		super("SUPPRIMER");
		roomId = id;
	}
	@Override
	public void execute() throws RemoteException {
		server.deleteDraftRoom(roomId);
	}
}
