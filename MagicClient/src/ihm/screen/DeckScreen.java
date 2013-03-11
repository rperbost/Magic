package ihm.screen;

import ihm.buttons.SaveDeckButton;
import ihm.card.CardBumpOnHover;
import ihm.card.CardDraggableDeck;
import ihm.card.CardDrawable;
import ihm.card.CardHandOnHover;
import ihm.card.CardPositionned;
import ihm.card.CardZoomUnderMouse;

import java.awt.Color;
import java.awt.GridLayout;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import binder.interfaces.Booster;
import binder.interfaces.Card;
import binder.interfaces.Deck;
import binder.interfaces.Card.Land;

@SuppressWarnings("serial")
public class DeckScreen extends Screen{
	private Deck deck;
	Map <Card.Land, Card> lands = new HashMap<Card.Land,Card>();
	String landSet = null;
	
	public DeckScreen(Deck deck) throws RemoteException{
		this.landSet = "13m";
		this.deck = deck;
		this.init();
		this.refresh();
	}

	public Deck getDeck() {
		return deck;
	}
	
	public void init() {
		
		this.landSet = "13m";
		
		try {
			this.lands.put(Land.plains,masterBinder.get("Plains"));
			this.lands.put(Land.island,masterBinder.get("Island"));
			this.lands.put(Land.swamp,masterBinder.get("Swamp"));
			this.lands.put(Land.mountain,masterBinder.get("Mountain"));
			this.lands.put(Land.forest,masterBinder.get("Forest"));
		} catch (RemoteException e) {}
		
		this.addPanel("DECK", new JPanel())
		.addPanel("LAND-STACK",new JPanel())
		.addPanel("TRASH",new JPanel())
		.addPanel("CMD",new JPanel())
		.addPanel("SIDEBOARD",new JPanel());
	
		this.panel("DECK").setBounds(0, 0, 576, 500);
		this.panel("DECK").setBackground(new Color(0,0,255,100));
		this.panel("DECK").setOpaque(true);
		
		this.panel("TRASH").setBounds(500, 500, 100, 100);
		this.panel("TRASH").setBackground(new Color(255,0,0,100));
		this.panel("TRASH").setOpaque(true);
		
		this.panel("CMD").setBounds(600, 500, 200, 100);
		this.panel("CMD").setBackground(new Color(255,255,0,100));
		this.panel("CMD").setOpaque(true);
		this.panel("CMD").setLayout(new GridLayout(1,1));
		try {
			this.panel("CMD").add(new SaveDeckButton(deck));
			this.panel("CMD").repaint();
		} catch (RemoteException e) {
		}
	
		this.panel("LAND-STACK").setBounds(0, 500, 500, 100);
		this.panel("LAND-STACK").setBackground(new Color(0,0,255));
	
		this.panel("SIDEBOARD").setBounds(574, 0, 218, 500);
		this.panel("SIDEBOARD").setBackground(new Color(0,255,0,100));
		this.panel("SIDEBOARD").setOpaque(true);
	}
	
	public void refresh() throws RemoteException {
		this.refreshLand();
		this.refreshDeck();
		this.refreshSideboard();
		super.refresh();
	};
	
	
	public void refreshDeck() throws RemoteException{
		JPanel p = this.panel("DECK");
		p.removeAll();
		
		Booster b = deck.getMainDeck();
		for(int i = b.size()-1; i >= 0 ;i--){
			CardDrawable c = new CardDrawable(b.get(i),CardDrawable.OVERVIEW_WIDTH,CardDrawable.OVERVIEW_HEIGHT);
			c = new CardPositionned((i%5)*(c.getWidth()+5)+2,i/5*15,c);
			c = new CardHandOnHover(c);
			c = new CardZoomUnderMouse(c);
			c = new CardDraggableDeck(this.deck,c);
			p.add(c);
		}
	}

	public void refreshLand() throws RemoteException {
		JPanel p = this.panel("LAND-STACK");
		p.removeAll();
		
		List<Card.Land> landCards = new ArrayList<Card.Land>();
		
		landCards.add(Land.plains);
		landCards.add(Land.island);
		landCards.add(Land.swamp);
		landCards.add(Land.mountain);
		landCards.add(Land.forest);
		
		for(int i = 0; i < landCards.size() ;i++){
			CardDrawable c = new CardDrawable(lands.get(landCards.get(i)),CardDrawable.OVERVIEW_WIDTH,CardDrawable.OVERVIEW_HEIGHT);
			c = new CardPositionned(i*(c.getWidth()), 10, c);
			c = new CardHandOnHover(c);
			c = new CardBumpOnHover(c);
			c = new CardZoomUnderMouse(c);
			c = new CardDraggableDeck(this.deck,c);

			p.add(c);
		}
	}
	private void refreshSideboard() throws RemoteException {
		JPanel p = this.panel("SIDEBOARD");
		p.removeAll();
		
		Booster b = deck.getSideboard();
		for(int i = b.size()-1; i >= 0 ;i--){
			CardDrawable c = new CardDrawable(b.get(i),CardDrawable.OVERVIEW_WIDTH,CardDrawable.OVERVIEW_HEIGHT);
			c = new CardPositionned((i%2)*(c.getWidth()+6)+7,i/2*15,c);
			c = new CardHandOnHover(c);
			c = new CardZoomUnderMouse(c);
			c = new CardDraggableDeck(this.deck,c);
			p.add(c);
		}
	}	
}
