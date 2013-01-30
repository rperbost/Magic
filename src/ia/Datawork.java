package ia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import binder.Card;
import binder.SingletonBinder;

public class Datawork {
	private static final int NB_MODIFICATION_AVANT_SAVE = 10;
	private static final double PLUS_INCREMENT = 1.0;
	private static final double MINUS_INCREMENT = -1.0;
	private static int NB_MODIFICATION = 0;
	
	private SingletonBinder binder;
	
	private static Map<String,Double>loadedRef = null;
	
	
	public Datawork(){
		File iaDir = new File("ia");
		 if (!iaDir.exists())iaDir.mkdir();
		
		 if(loadedRef == null){
			 grabExistingData();
			 mergeBinderToData();
			 saveData();
		 }
		 
		 
	}

	private void saveData() {
		File iaF = new File("ia/iaDatas.csv");
		iaF.delete();
		
		
		try {
			FileWriter fstream = new FileWriter("ia/iaDatas.csv");
			BufferedWriter out = new BufferedWriter(fstream);
			
			Iterator<String> it = loadedRef.keySet().iterator();
			
			while(it.hasNext()){
				String itS = it.next();		
				out.write(itS+"\t"+loadedRef.get(itS)+"\n");
			}
			
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		  
		
	}

	private void mergeBinderToData() {
		binder = SingletonBinder.getInstance();
		
		 File setsDir = new File("sets");
		 if (!setsDir.exists())setsDir.mkdir();
		 
		 ArrayList<File> files = new ArrayList<File>(Arrays.asList(setsDir.listFiles()));
			
			for(int i = 0;i < files.size(); i++){
				File file = files.get(i);
				try {
					BufferedReader lignes = new BufferedReader(new FileReader(file));
					
					String ligne;
					
					try {
						while((ligne = lignes.readLine()) != null){
							String ref = Card.parseCsv(ligne).getReference();
							if(!loadedRef.containsKey(ref)){
								loadedRef.put(ref,0.0);
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		
	}

	private void grabExistingData() {
		
		loadedRef = new HashMap<String,Double>();
		
		File iaF = new File("ia/iaDatas.csv");
		if(iaF.exists()){
			try {
				BufferedReader lignes = new BufferedReader(new FileReader(iaF));
				
				String ligne;
				
				try {
					while((ligne = lignes.readLine()) != null){
						String ligneSplit[] = ligne.split("\t");
						if(!loadedRef.containsKey(ligneSplit[0])){
							loadedRef.put(ligneSplit[0],Double.parseDouble(ligneSplit[1]));
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	public void modifIa(String refGagnant,String refPerdant){
		if(loadedRef.containsKey(refGagnant) && loadedRef.containsKey(refPerdant)){
			loadedRef.put(refGagnant, loadedRef.get(refGagnant)+PLUS_INCREMENT);
			loadedRef.put(refPerdant, loadedRef.get(refPerdant)+MINUS_INCREMENT);
			NB_MODIFICATION++;
			if(NB_MODIFICATION>=NB_MODIFICATION_AVANT_SAVE){
				saveData();
				NB_MODIFICATION=0;
			}
		}
	}
	
	public String[] getRefs(){
		List<String> refs = new ArrayList<String>(loadedRef.keySet());
		String ref1 = refs.get((int)(Math.random()*refs.size()));
		String ref2 = "";
		do{
			ref2 = refs.get((int)(Math.random()*refs.size()));
		}while(ref1.equals(ref2));
		String retournedRefs[] = {ref1,ref2};
		return retournedRefs;
	}
	
	public String[] getRefs(String set){
		List<String> refs = new ArrayList<String>(loadedRef.keySet());
		
		String ref1 = "";
		do{
			ref1 = refs.get((int)(Math.random()*refs.size()));
		}while(!ref1.substring(0, 3).equals(set));
		String ref2 = "";
		do{
			ref2 = refs.get((int)(Math.random()*refs.size()));
		}while(ref1.equals(ref2)||!ref2.substring(0, 3).equals(set));
		String retournedRefs[] = {ref1,ref2};
		return retournedRefs;
	}

	public String[] getRefsWSameSet() {
		List<String> refs = new ArrayList<String>(loadedRef.keySet());
		
		String ref1 = refs.get((int)(Math.random()*refs.size()));
		
		return getRefs(ref1.substring(0,3));
	}
}