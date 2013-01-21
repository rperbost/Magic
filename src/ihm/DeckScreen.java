package ihm;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import binder.Binder;
import binder.Booster;
import binder.Card;
import binder.Card.Land;
import binder.CardBinder;
import binder.Deck;
import binder.SingletonBinder;

import player.Player;

@SuppressWarnings("serial")
public class DeckScreen extends Screen{
	
	String landSet;
	Map <Card.Land, Card> lands = new HashMap<Card.Land,Card>();
	private Deck deck;
	
	public DeckScreen(String landSet, Deck deck){
		this.setLandSet(landSet);
		this.setDeck(deck);
		this.addPanel("LEFT", new JPanel())
		.addPanel("BOTTOM",new JPanel())
		.addPanel("RIGHT",new JPanel());
	
		this.panel("LEFT").setBounds(0, 0, 500, 500);
		this.panel("LEFT").setBackground(new Color(255,255,0));
	
		this.panel("BOTTOM").setBounds(0, 500, 800, 100);
		this.panel("BOTTOM").setBackground(new Color(0,0,255));
	
		this.panel("RIGHT").setBounds(500, 0, 300, 500);
		this.panel("RIGHT").setBackground(new Color(0,255,255));
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public void setLandSet(String set) {
		SingletonBinder masterBinder = SingletonBinder.getInstance();
		
		CardBinder setAsBinder = masterBinder.getSet(set);		
		
		if(
			setAsBinder.contains("Plains")
			&& setAsBinder.contains("Island")
			&& setAsBinder.contains("Swamp")
			&& setAsBinder.contains("Mountain")
			&& setAsBinder.contains("Forest")		
		){
			this.landSet = set;
			this.lands.put(Land.plains,setAsBinder.get("Plains"));
			this.lands.put(Land.island,setAsBinder.get("Island"));
			this.lands.put(Land.swamp,setAsBinder.get("Swamp"));
			this.lands.put(Land.mountain,setAsBinder.get("Mountain"));
			this.lands.put(Land.forest,setAsBinder.get("Forest"));
		}
		
		
		
		
		
	}

	
	
}
