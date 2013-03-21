package datamanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

import binder.Card;
import binder.IBinder;
import binder.ICard;

public class CsvManager implements IDatabaseManager{
	
	String filePath;
	
	public CsvManager() {
		File databaseManagerDir = new File("DatabaseManager");
		if (!databaseManagerDir.exists())databaseManagerDir.mkdir();
		filePath = "DatabaseManager/magicCardsDatabase.csv";
	}

	public String cardToCsv(ICard card) throws RemoteException{
		String retour = "";
		retour+=card.getName()+"\t";
		retour+=card.getSet()+"\t";
		retour+=card.getIndex()+"\t";
		retour+=card.getType()+"\t";
		retour+=card.getRarity();
		return retour;
	}

	public Card csvToCard(String ligneCsv)  throws RemoteException{
		ArrayList<String> cardAttributes = new ArrayList<String>(Arrays.asList(ligneCsv.split("\t")));
		return new Card(
				cardAttributes.get(0),
				cardAttributes.get(1),
				cardAttributes.get(2),
				cardAttributes.get(3),
				Card.parseRarity(cardAttributes.get(4))
		);
	}

	@Override
	public void load(IBinder binder) {
		
		try {
			BufferedReader lignes = new BufferedReader(new FileReader(filePath));
			String ligne = "";
			while((ligne = lignes.readLine()) != null){
				binder.addCard(csvToCard(ligne));
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		
	}
	
	@Override
	public void save(IBinder binder) {
		try {
			FileWriter fw = new FileWriter(filePath);
			BufferedWriter output = new BufferedWriter(fw);
			for(int c = 0; c < binder.size(); c++){
				output.write(this.cardToCsv(binder.get(c))+"\n");
			}
			output.flush();
			output.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
