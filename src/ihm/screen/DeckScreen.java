package ihm.screen;

import ihm.card.DragableCard;
import ihm.card.DrawableCard;
import ihm.card.LandCard;
import ihm.card.ZoomedCard;

import java.awt.Color;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	
	String landSet = null;
	Map <Card.Land, Card> lands = new HashMap<Card.Land,Card>();
	private Deck deck;
	
	public DeckScreen(String landSet, Deck deck){
		this.setLandSet(landSet);
		this.setDeck(deck);
		this.addPanel("DECK", new JPanel())
		.addPanel("LAND-STACK",new JPanel())
		.addPanel("TRASH",new JPanel())
		.addPanel("ZOOM",new JPanel())
		.addPanel("SIDEBOARD",new JPanel());
	
		this.panel("DECK").setBounds(0, 0, 420, 500);
		this.panel("DECK").setBackground(new Color(0,0,255,100));
		this.panel("DECK").setOpaque(true);
		
		this.panel("ZOOM").setBounds(418, 0, 156, 222);
		this.panel("ZOOM").setBackground(new Color(255,0,255));
		
		this.panel("TRASH").setBounds(300, 500, 500, 100);
		this.panel("TRASH").setBackground(new Color(255,0,0,100));
		this.panel("TRASH").setOpaque(true);
	
		this.panel("LAND-STACK").setBounds(0, 500, 300, 100);
		this.panel("LAND-STACK").setBackground(new Color(0,0,255));
	
		this.panel("SIDEBOARD").setBounds(574, 0, 218, 500);
		this.panel("SIDEBOARD").setBackground(new Color(0,255,0,100));
		this.panel("SIDEBOARD").setOpaque(true);
		
		this.refreshBottom();
		this.refreshDeck();
	}

	public Deck getDeck() {
		return deck;
	}

	public void refreshDeck(){
		JPanel p = this.panel("DECK");
		p.removeAll();
		
		Booster b = deck.getMainDeck();
		for(int i = b.size()-1; i >= 0 ;i--){
			DragableCard c = new DragableCard(b.get(i), this);
			c.setLocation((i%4)*(DrawableCard.CARD_WIDTH+5)+2,i/4*15);
			p.add(c);
		}
		
		p = this.panel("SIDEBOARD");
		p.removeAll();
		
		b = deck.getSideboard();
		for(int i = b.size()-1; i >= 0 ;i--){
			DragableCard c = new DragableCard(b.get(i), this);
			c.setLocation((i%2)*(DrawableCard.CARD_WIDTH+6)+7,i/2*15);
			p.add(c);
		}
		this.repaint();
	}
	public void refreshBottom() {
		JPanel p = this.panel("LAND-STACK");
		p.removeAll();
		
		List<LandCard> landCards = new ArrayList<LandCard>();
		
		landCards.add(new LandCard(lands.get(Land.plains),this));
		landCards.add(new LandCard(lands.get(Land.island),this));
		landCards.add(new LandCard(lands.get(Land.swamp),this));
		landCards.add(new LandCard(lands.get(Land.mountain),this));
		landCards.add(new LandCard(lands.get(Land.forest),this));
		
		for(int i = 4; i >= 0; i--){
			landCards.get(i).setLocation((i*DrawableCard.CARD_WIDTH/2),5);
			p.add(landCards.get(i));
		}
		
		this.repaint();
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public void setLandSet(String set) {
		SingletonBinder masterBinder = SingletonBinder.getInstance();
		
		CardBinder setAsBinder = masterBinder.getSet(set);		
		
		if(
			setAsBinder.isStandAlone()	
		){
			this.landSet = set;
			this.lands.put(Land.plains,setAsBinder.get("Plains"));
			this.lands.put(Land.island,setAsBinder.get("Island"));
			this.lands.put(Land.swamp,setAsBinder.get("Swamp"));
			this.lands.put(Land.mountain,setAsBinder.get("Mountain"));
			this.lands.put(Land.forest,setAsBinder.get("Forest"));
		}
	}

	public void repaint(){
		super.repaint();		
	}

	public void setZoom(ZoomedCard zoomedCard) {
		this.panel("ZOOM").removeAll();
		this.panel("ZOOM").add(zoomedCard);
		zoomedCard.setVisible(true);
		zoomedCard.setLocation(0,0);
		this.repaint();
	}
	
}
