package binder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SingletonBinder extends SetBinder{
	static SingletonBinder theInstance = null;
	
	static public SingletonBinder getInstance(){
		if(theInstance == null){
			theInstance = new SingletonBinder();
		}
		return theInstance;
	}
	
	private SingletonBinder(){}

	public void addFile(File file) {
		
		try {
			BufferedReader lignes = new BufferedReader(new FileReader(file));
			
			String ligne;
			
			try {
				while((ligne = lignes.readLine()) != null){
					this.addCard(Card.parseCsv(ligne));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
