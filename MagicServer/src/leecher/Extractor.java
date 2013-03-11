package leecher;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import binder.ImplementedCard;

public class Extractor {
	
	public void execute() throws IOException{
		long debutTraitement = System.currentTimeMillis();
		Vector<String> editions = MagicVilleLeecher.getEditions();
		
		File setsDir = new File("sets");
		 if (!setsDir.exists())setsDir.mkdir();
		
		for(int e = 0;e < editions.size();e++){
			File set = new File("sets/"+editions.get(e)+".csv");
			System.out.println("["+(System.currentTimeMillis() - debutTraitement)+"]traitement de "+e+" : "+editions.get(e));
			if(set.exists())continue;
			
			
			
			Vector<ImplementedCard> cardSet = MagicVilleLeecher.getCardsList(editions.get(e));
			
			FileWriter fw = new FileWriter("sets/"+editions.get(e)+".csv", true);
			BufferedWriter output = new BufferedWriter(fw);
			for(int c = 0; c < cardSet.size(); c++){
				output.write(cardSet.get(c).getCsv()+"\n");
			}
			output.flush();
			output.close();		
		}
		
		System.out.println("fin");
		
	}
	
	
}
