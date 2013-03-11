package binder.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Card extends Remote{
	public enum Land {
		forest,island,mountain,plains,swamp
	}
	public enum Rarity {
	    common, mythic, rare, timeshifted, uncommon, unknow 
	}
	
	public boolean equals(Card card) throws RemoteException;
	public String getCsv() throws RemoteException;
	public String getIndex() throws RemoteException;
	public String getName() throws RemoteException;
	public Rarity getRarity() throws RemoteException;
	public String getReference() throws RemoteException;
	public String getSet() throws RemoteException;

}