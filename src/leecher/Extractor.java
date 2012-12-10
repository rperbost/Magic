package leecher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

import binder.Card;

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
			
			
			
			Vector<Card> cardSet = MagicVilleLeecher.getCardsList(editions.get(e));
			
			FileWriter fw = new FileWriter("sets/"+editions.get(e)+".csv", true);
			BufferedWriter output = new BufferedWriter(fw);
			for(int c = 0; c < cardSet.size(); c++){
				output.write(cardSet.get(c).getCsv()+"\n");
			}
			output.flush();
			output.close();
			
			
		}
		
		for(int e = 0;e < editions.size();e++){
			ImportImages("sets/"+editions.get(e)+".csv");
		}
		
		System.out.println("fin");
		
	}
	
	public static void GetFile(String origin,String destination){
		URL url;
        URLConnection con;
        DataInputStream dis;
        FileOutputStream fos;
        byte[] fileData;
        try {
                url = new URL(origin);
                con = url.openConnection();
                dis = new DataInputStream(con.getInputStream());
                fileData = new byte[con.getContentLength()];
                for (int x = 0; x < fileData.length; x++) {
                        fileData[x] = dis.readByte();
                }
                dis.close();
                fos = new FileOutputStream(new File(destination));
                fos.write(fileData);
                fos.close();
        }
        catch(MalformedURLException m) {
                System.out.println(m);
        }
        catch(IOException io) {
                System.out.println(io);
        }
	}
	
	public static void ImportImages(String file){
		 try {
			 File picsDir = new File("pics");
			 if (!picsDir.exists())picsDir.mkdir(); 
			 File setDirB = new File("pics/big");
			 if (!setDirB.exists())setDirB.mkdir();
			 
			 File setDirL = new File("pics/lil");
			 if (!setDirL.exists())setDirL.mkdir();
		      FileInputStream fstream = new FileInputStream(file);
		      DataInputStream in = new DataInputStream(fstream);
		      BufferedReader br = new BufferedReader(new InputStreamReader(in));
		      String ligne;
		      while ((ligne = br.readLine()) != null) {
		    	
		        String tableau[] = ligne.split("\t");
		        String set = tableau[1];
		        String index = tableau[2];
		        
		         
		        
		        File setDir = new File("pics/big/"+set);
				 if (!setDir.exists())setDir.mkdir(); 
				 File setDir2 = new File("pics/lil/"+set);
				 if (!setDir2.exists())setDir2.mkdir(); 
				 
				 
				 
		        File f = new File("pics/big/"+set+"/"+index+".jpg");
		        if(!f.exists()) {
		        	System.out.println("Extraction d'Image (big) : "+ligne);
		        	f.createNewFile();
		        	Extractor.GetFile(
		        			"http://www.magic-ville.com/pics/big/"+set+"/"+index+".jpg",
		        			"pics/big/"+set+"/"+index+".jpg"
		        	);		        	
		        }
		        File f2 = new File("pics/lil/"+set+"/"+index+".jpg");
		        if(!f2.exists()) {
		        	System.out.println("Extraction d'Image (lil) : "+ligne);
		        	f2.createNewFile();
		        	Extractor.GetFile(
		        			"http://www.magic-ville.com/pics/lil/"+set+"/"+index+".jpg",
		        			"pics/lil/"+set+"/"+index+".jpg"
		        	);		        	
		        }
		        
		      }
		      in.close();
		    } catch (Exception e) {
		      System.err.println("boucle1 "+e);
		    }
	}
}
