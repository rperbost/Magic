package datamanager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

import binder.Card;
import binder.IBinder;
import binder.ICard;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XmlManager implements IDatabaseManager{
	
	private String filePath;

	public XmlManager() {
		File databaseManagerDir = new File("DatabaseManager");
		if (!databaseManagerDir.exists())databaseManagerDir.mkdir();
		filePath = "DatabaseManager/magicCardsDatabase.xml";
	}

	public Element cardToXml(ICard card) throws RemoteException{
		Element cardNode = new Element("card");
		cardNode.addContent(new Element("name").setText(card.getName()));
		cardNode.addContent(new Element("set").setText(card.getSet()));
		cardNode.addContent(new Element("index").setText(card.getIndex()));
		cardNode.addContent(new Element("type").setText(card.getType()));
		cardNode.addContent(new Element("rarity").setText(card.getRarity().toString()));
		return cardNode;
	}

	@Override
	public void load(IBinder binder) {
		
		SAXBuilder sxb = new SAXBuilder();
	    try{
	       Document document = sxb.build(new File(filePath));
	       Element racine = document.getRootElement();
	       List<Element> cards = racine.getChildren("card");
	       for(Element cardNode:cards){
	    	   binder.addCard(xmlToCard(cardNode));
	       }
	    }catch(Exception e){
	    	 e.printStackTrace();
	    }
	}
	
	@Override
	public void save(IBinder binder) {
		Element root = new Element("magic");
		Document document = new Document(root);
		
		try {
			for(int i = 0; i < binder.size(); i++){
				root.addContent(cardToXml(binder.get(i)));
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		try {
            XMLOutputter xmlop = new XMLOutputter (Format.getPrettyFormat());
            xmlop.output(document, new FileOutputStream(new File(filePath)));
        } catch (IOException e) {}
	}
	
	public Card xmlToCard(Element cardNode)  throws RemoteException{
		return new Card(
				cardNode.getChildText("name"),
				cardNode.getChildText("set"),
				cardNode.getChildText("index"),
				cardNode.getChildText("type"),				
				Card.parseRarity(cardNode.getChildText("rarity"))
		);
	}
}
