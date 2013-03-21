package player;

import java.rmi.RemoteException;

import binder.IBooster;

public class IAPlayer extends IPlayer{

	public IAPlayer(IPlayer player) throws RemoteException {
		super(player);
		try{
			super.setSelectedCard(currentBooster.get(0));
		}catch(Exception e){
			
		}
	}
	
	public IAPlayer(String name) throws RemoteException {
		super(name);
	}

	public void setCurrentBooster(IBooster currentBooster) throws RemoteException{
		super.setCurrentBooster(currentBooster);
		super.setSelectedCard(currentBooster.get(0));
	}
	
}
