package launcher;

import java.rmi.RemoteException;

import ihm.MainFrame;
import ihm.screen.ServerSelectionScreen;

public class MagicClient {
	public static void main(String args[]) throws RemoteException{
		
		MainFrame mainFrame = MainFrame.getInstance();
		
		mainFrame.addScreen( "ENTRY" , new ServerSelectionScreen() ).activeScreen("ENTRY");
		
	}	
}
