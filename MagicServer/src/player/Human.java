package player;

import java.rmi.Naming;
import java.rmi.RemoteException;

import binder.interfaces.Deck;

import rmi.ClientCallBack;


public class Human extends Player{

	ClientCallBack ccb = null;
	
	public Human(String name,int id) throws RemoteException {
		super(name);
		this.setId(id);
	}

	private void initCallBack() {
		if(ccb == null){
			try {
				ccb = (ClientCallBack)Naming.lookup("//localhost:2020/MagicClient_"+id);
			} catch (Exception e) {
			}
		}
	}
	
	@Override
	public boolean ping() throws RemoteException{
		initCallBack();
		return ccb.ping();
	}
	
	public void refresh() throws RemoteException{
		initCallBack();
		ccb.refresh();
	}

	@Override
	public void startDeckListScreen(Deck deck) throws RemoteException{
		initCallBack();
		ccb.startDeckListScreen(deck);
	}
	
	@Override
	public void startDraftScreen() throws RemoteException{
		initCallBack();
		ccb.startDraftScreen();
	}
}
