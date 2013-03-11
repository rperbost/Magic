package ihm.buttons;

import java.rmi.RemoteException;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class CreateRoomButton extends CommandButton{
	JComboBox set1,set2,set3;
	public CreateRoomButton(JComboBox set1, JComboBox set2, JComboBox set3) throws RemoteException{
		super("Creer une Salle");
		this.set1 = set1;
		this.set2 = set2;
		this.set3 = set3;
	}
	@Override
	public void execute() throws RemoteException {
		server.createDraftRoom((String)set1.getSelectedItem(), (String)set2.getSelectedItem(), (String)set3.getSelectedItem());
	}
}
