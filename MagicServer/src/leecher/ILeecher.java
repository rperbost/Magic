package leecher;

import java.rmi.RemoteException;

import datamanager.IDatabaseManager;

import binder.IBinder;

public interface ILeecher {
	public void execute(IBinder binder, IDatabaseManager databaseManager) throws RemoteException;
}
