package player;

import java.rmi.Naming;
import java.rmi.RemoteException;

import binder.IDeck;

import rmi.IClient;


public class HumanPlayer extends IPlayer{

	IClient ccb = null;
	
	public HumanPlayer(String name,int id) throws RemoteException {
		super(name);
		this.setId(id);
	}

	private void initCallBack() {
		if(ccb == null){
			try {
				ccb = (IClient)Naming.lookup("//"+"localhost"+":2020/MagicClient_"+id);
			} catch (Exception e) {
				e.printStackTrace();
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
	public void startDeckListScreen(IDeck deck) throws RemoteException{
		initCallBack();
		ccb.startDeckListScreen(deck);
	}
	
	@Override
	public void startDraftScreen() throws RemoteException{
		initCallBack();
		ccb.startDraftScreen();
	}
}
