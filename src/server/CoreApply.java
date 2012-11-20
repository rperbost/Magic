package server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import binder.BoosterFactory;
import binder.SingletonBinder;

import leecher.Extractor;

public class CoreApply {

	static CoreApply theInstance = null;
	
	private SingletonBinder megaBinder;
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
