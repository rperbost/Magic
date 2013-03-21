package datamanager;

import binder.IBinder;

public interface IDatabaseManager {
	public void load(IBinder binder);
	public void save(IBinder binder);
}
