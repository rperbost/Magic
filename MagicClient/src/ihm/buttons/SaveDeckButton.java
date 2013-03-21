package ihm.buttons;

import ihm.MainFrame;

import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import binder.IDeck;

@SuppressWarnings("serial")
public class SaveDeckButton extends CommandButton{
	IDeck deck;
	public SaveDeckButton(IDeck deck) throws RemoteException{
		super("Sauvegarder le deck");
		this.deck = deck;
	}
	@Override
	public void execute() throws RemoteException {
		System.out.println();
		JFileChooser jfs = new JFileChooser();
		jfs.setDialogTitle("Enregistrer le deck");
		jfs.setApproveButtonText("Sauvegarder");
		jfs.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfs.setFileFilter(new FileNameExtensionFilter("Cockatrice (.cod)","cod"));
		int jfs_retour = jfs.showOpenDialog(MainFrame.getInstance());
		if(jfs_retour == JFileChooser.APPROVE_OPTION){
			try {
				FileWriter fw = new FileWriter(jfs.getSelectedFile());
				fw.write(deck.toCodString());
				fw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
