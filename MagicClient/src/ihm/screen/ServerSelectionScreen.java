package ihm.screen;

import ihm.buttons.CreateRoomButton;
import ihm.buttons.DeleteRoomButton;
import ihm.buttons.JoinRoomButton;
import ihm.buttons.LeaveRoomButton;
import ihm.buttons.StartDraftButton;

import java.awt.Color;
import java.awt.GridLayout;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ServerSelectionScreen extends Screen{
	public ServerSelectionScreen() throws RemoteException {
		this.init();
		this.refresh();
	}
	public void init(){
		this.addPanel("TOP", new JPanel())
		.addPanel("BOTTOM", new JPanel());
		
		this.panel("TOP").setBounds(0, 0, 800, 100);
		this.panel("TOP").setBackground(Color.red);
		this.panel("TOP").setOpaque(true);
		
		this.panel("BOTTOM").setBounds(0, 100, 800, 500);
		this.panel("BOTTOM").setBackground(Color.yellow);
		
		this.panel("BOTTOM").setLayout(new GridLayout(0,4));
	}
	
	public void refresh() throws RemoteException{
		ServerListParser serverList = new ServerListParser(server.getServerList());
		
		JPanel p = this.panel("BOTTOM");
		p.removeAll();
		for(int i = 0 ; i < serverList.size() && i < 10;i++){
			JLabel roomLabel = new JLabel(serverList.toString(i));
			roomLabel.setOpaque(true);
			if(serverList.imIn(i))roomLabel.setBackground(Color.green);
			else roomLabel.setBackground(Color.gray);
			p.add(roomLabel);

			if(serverList.size(i) < 8  && serverList.getMyActualRoom() == -1){
				p.add(new JoinRoomButton(serverList.getOwner(i)));
			}else p.add(new JLabel(" "));
			
		}
		p.validate();
		
		p = this.panel("TOP");
		p.removeAll();
		int ownedRoom = serverList.getOwnedRoom();
		int actualRoom = serverList.getMyActualRoom();
		if(ownedRoom == -1 && actualRoom == -1){
			p.setLayout(new GridLayout(1,4));
			Vector<String> setList = new Vector<String>();
			setList.addAll(server.getServer().getBinder().getSetList());
			JComboBox set1 = new JComboBox(setList);
			JComboBox set2 = new JComboBox(setList);
			JComboBox set3 = new JComboBox(setList);
			p.add(set1);
			p.add(set2);
			p.add(set3);
			p.add(new CreateRoomButton(set1,set2,set3));
		}else{
			if(ownedRoom != -1){
				p.setLayout(new GridLayout(1,3));
				p.add(new JLabel(serverList.toString(ownedRoom)));
				p.add(new DeleteRoomButton(ownedRoom));
				p.add(new StartDraftButton());
			}
			else{
				p.setLayout(new GridLayout(1,2));
				p.add(new JLabel(serverList.toString(actualRoom)));
				p.add(new LeaveRoomButton(actualRoom));
			}
			
		}
		
		
		p.validate();
		super.refresh();
	}
	
}
