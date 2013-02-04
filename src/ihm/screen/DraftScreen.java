package ihm.screen;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import binder.Booster;
import binder.Deck;

import player.Player;

import ihm.card.*;

@SuppressWarnings("serial")
public class DraftScreen extends Screen{
	public Player player;
	private JPanel readyContainer;
	private JPanel[] ready;
	public DraftScreen(Player player) {
		this.player = player;
		this.init();
		this.refresh();
	}
	
	
	public void init() {
		this.addPanel("LEFT", new JPanel())
		.addPanel("TOP",new JPanel())
		.addPanel("RIGHT",new JPanel());
	
		this.panel("LEFT").setBounds(0, 0, 204, 600);
		this.panel("LEFT").setBackground(new Color(255,255,0));
	
		this.panel("TOP").setBounds(204, 0, 596, 50);
		this.panel("TOP").setBackground(new Color(0,0,255));
	
		this.panel("RIGHT").setBounds(204, 50, 596, 750);
		this.panel("RIGHT").setBackground(new Color(0,255,255));
		
		readyContainer = new JPanel();
		readyContainer.setLocation(46,5);
		readyContainer.setSize(504 ,40);
		readyContainer.setVisible(true);
		readyContainer.setLayout(null);
		readyContainer.setBackground(new Color(255,255,0));
		
		ready = new JPanel[8];

		for(int i = 0; i < 8; i++){
			ready[i] = new JPanel();
			ready[i].setLocation(readyContainer.getWidth()/8*i,0);
			ready[i].setSize(readyContainer.getWidth()/8 ,40);
			ready[i].setVisible(true);
			
			readyContainer.add(ready[i]);
		}
	}
	public void refresh(){
		this.refreshReady();
		this.refreshBooster();
		this.refreshDeck();
		super.refresh();
	}
	
	private int nbReady = -1;
	boolean pseudoLoaded = false;
	public void refreshReady(){
		boolean[] readys = player.getReadyStates();
		if(readys != null){
			if(readys.length != nbReady){
				JPanel p = this.panel("TOP");
				p.removeAll();
				
				for(int i = 0; i < 8; i++){
					if(readys[i]){
						ready[i].setBackground(new Color(0,255,0));
					}else{
						ready[i].setBackground(new Color(255,0,0));
					}
				}
				
				if(!pseudoLoaded){
					pseudoLoaded = true;
					String[] pseudos = player.getPseudos();
					for(int i = 0; i < 8; i++){
						JLabel jl = new JLabel(pseudos[i]);
						ready[i].add(jl);
					}
				}
				
				p.add(readyContainer);
			}
		}
	}
	
	private int deckSize = -1;
	private void refreshDeck() {
		Deck deck = player.getDeck();
		if (deck!=null){
			Booster mainDeck = deck.getMainDeck();
			if(mainDeck.size() != deckSize){
				deckSize = mainDeck.size();	
				JPanel p = this.panel("LEFT");
				p.removeAll();
				for(int i = 0;i < mainDeck.size() ;i++){
					CardDrawable c = new CardDrawable(mainDeck.get(i),CardDrawable.OVERVIEW_WIDTH,CardDrawable.OVERVIEW_HEIGHT);
					c = new CardPositionned((c.getWidth()+2)*(i%2)+1, 15+7*i, c);
					c = new CardZoomUnderMouse(c);
					p.add(c,0);
				}
			}
		}
	}

	private int boosterSize = -1;
	private void refreshBooster() {
		Booster booster = player.getBooster();
		if (booster!=null && booster.size()!=boosterSize){
			boosterSize = booster.size();			
			JPanel p = this.panel("RIGHT");
			p.removeAll();
			for(int i = 0;i < booster.size() ;i++){
				CardDrawable c = new CardDrawable(booster.get(i),CardDrawable.OVERVIEW_WIDTH,CardDrawable.OVERVIEW_HEIGHT);
				c = new CardHandOnHover(c);
				c = new CardPositionned((i%5)*(c.getWidth()+15), (i/5)*(c.getHeight()+45), c);
				if(this.player.getSelectedCardId() == i)c = new CardSelected(c);
				c = new CardZoomUnderMouse(c);
				c = new CardSelectDraftCard(c);
				
				p.add(c,0);
			}
		}
	}
	public Player getPlayer() {
		return player;
	}
}
