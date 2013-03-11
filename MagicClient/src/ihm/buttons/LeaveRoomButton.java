package ihm.buttons;

import java.rmi.RemoteException;

@SuppressWarnings("serial")
public class LeaveRoomButton extends CommandButton {
	int roomId;
	public LeaveRoomButton(int id) throws RemoteException{
		super("QUITTER");
		roomId = id;
	}
	@Override
	public void execute() throws RemoteException {
		server.leaveDraftRoom(roomId);
	}
}
