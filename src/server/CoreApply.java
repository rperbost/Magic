package server;

import java.io.IOException;

import binder.BoosterFactory;
import binder.SingletonBinder;

import leecher.Extractor;

public class CoreApply {

	static CoreApply theInstance = null;
	
	@SuppressWarnings("unused")
	private SingletonBinder megaBinder;
	@SuppressWarnings("unused")
	private BoosterFactory boosterFactory;
	
	public static CoreApply getInstance(){
		if(theInstance == null)theInstance = new CoreApply();
		return theInstance;
	}
	private CoreApply(){
		this.initServer();
	}
	private void initServer() {
		
		Extractor diggingInformations = new Extractor();
		try {
			diggingInformations.execute();
		} catch (IOException e) {
			System.out.println("Init Error !");
			e.printStackTrace();
		}
		
		this.megaBinder = SingletonBinder.getInstance();
		this.boosterFactory = BoosterFactory.getInstance();
		
	}	
}
