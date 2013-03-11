package ihm.buttons;

import java.rmi.RemoteException;

@SuppressWarnings("serial")
public class JoinRoomButton extends CommandButton{
	int roomId;
	public JoinRoomButton(int id) throws RemoteException{
		super("REJOINDRE");
		roomId = id;
	}
	@Override
	public void execute() throws RemoteException {
		server.joinDraftRoom(roomId);
	}
}
