package server;

import java.io.IOException;

import leecher.Extractor;

public class CoreApply {

	static CoreApply theInstance = null;
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
		
	}
	
	
}
