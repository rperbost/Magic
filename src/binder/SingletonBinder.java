package binder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SingletonBinder extends SetBinder{
	static SingletonBinder theInstance = null;
	
	static public SingletonBinder getInstance(){
		if(theInstance == null){
			theInstance = new SingletonBinder();
		}
		return theInstance;
	}
	
	private SingletonBinder(){
		File f = new File("sets/");
		ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
		
		for(int i = 0;i < files.size(); i++){
			this.addFile(files.get(i));
		}
		//clean editions that contains less than 100cards
		Iterator<String> it = binder.keySet().iterator();
		
		List<String> toDelete = new ArrayList<String>();
		
		while(it.hasNext()){
			String set = it.next();
			if(binder.get(set).size()<100)toDelete.add(set);
		}
		
		while(toDelete.size()>0){
			binder.remove(toDelete.get(0));
			toDelete.remove(0);
		}
	}

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
