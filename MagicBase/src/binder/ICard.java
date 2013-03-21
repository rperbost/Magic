package binder;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICard extends Remote{
	public enum Land {
		forest,island,mountain,plains,swamp
	}
	public enum Rarity {
	    common, mythic, rare, timeshifted, uncommon, unknow 
	}
	
	public boolean equals(ICard card) throws RemoteException;
	public String getIndex() throws RemoteException;
	public String getName() throws RemoteException;
	public Rarity getRarity() throws RemoteException;
	public String getReference() throws RemoteException;
	public String getSet() throws RemoteException;
	public String getType() throws RemoteException;

}