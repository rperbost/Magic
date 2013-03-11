package player;

import java.rmi.RemoteException;

import binder.interfaces.Booster;

public class IA extends Player{

	public IA(Player player) throws RemoteException {
		super(player);
		try{
			super.setSelectedCard(currentBooster.get(0));
		}catch(Exception e){
			
		}
	}
	
	public IA(String name) throws RemoteException {
		super(name);
	}

	public void setCurrentBooster(Booster currentBooster) throws RemoteException{
		super.setCurrentBooster(currentBooster);
		super.setSelectedCard(currentBooster.get(0));
	}
	
}
