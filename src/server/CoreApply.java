package server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import binder.SingletonBinder;

import leecher.Extractor;

public class CoreApply {

	static CoreApply theInstance = null;
	
	private SingletonBinder megaBinder;
	
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
		
		initMegaBinder();
		
		
		
	}
	private void initMegaBinder() {
		this.megaBinder = SingletonBinder.getInstance();
		
		File f = new File("sets/");
		ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
		
		for(int i = 0;i < files.size(); i++){
			megaBinder.addFile(files.get(i));
		}
	}
	
	
}
