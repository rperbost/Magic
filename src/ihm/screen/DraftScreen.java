package ihm.screen;

import ihm.card.CardDrawable;
import ihm.card.CardHandOnHover;
import ihm.card.CardPositionned;
import ihm.card.CardSelectDraftCard;
import ihm.card.CardSelected;
import ihm.card.CardZoomUnderMouse;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import binder.Booster;

import player.Player;

@SuppressWarnings("serial")
public class DraftScreen extends Screen{
	
	private Player player;
	private JLabel timer;
	private JPanel[] ready;
	private JPanel readyContainer;
	private boolean pseudoLoaded = false;
	
	private Thread timerRefreshing;
	
	public DraftScreen(Player p){
		
		this.addPanel("LEFT", new JPanel())
			.addPanel("TOP",new JPanel())
			.addPanel("RIGHT",new JPanel());
		
		this.panel("LEFT").setBounds(0, 0, 204, 600);
		this.panel("LEFT").setBackground(new Color(255,255,0));
		
		this.panel("TOP").setBounds(204, 0, 596, 50);
		this.panel("TOP").setBackground(new Color(0,0,255));
		
		this.panel("RIGHT").setBounds(204, 50, 596, 750);
		this.panel("RIGHT").setBackground(new Color(0,255,255));
		
		this.setPlayer(p);
		
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
		
		timer = new JLabel();
		timer.setVisible(true);
		timer.setSize(100,20);
		timer.setLocation(280,5);
		
		timerRefreshing = new Thread(){
			public void run(){
				while(true){
					try{
						timer.setText(">"+(player.getTimer()/1000)+"<");
						Thread.sleep(500);
					}catch(Exception e){
						
					}
				}
			}
		};
		timerRefreshing.start();
	}
	
	public Screen setPlayer(Player p){
		this.player = p;
		
		return this;
	}
	
	private int nbCardsInBooster = -1;
	private int currentSelected = -1;
	public void repaint(){
		if(player != null){
			Booster booster = this.player.getBooster();
			if(booster != null 
					&& (nbCardsInBooster != booster.size()
					|| player.getSelectedCardId() != currentSelected)
			){
			this.refreshRight();
			};
		}
		super.repaint();
	}

	
	private void refreshRight() {
		Booster booster = this.player.getBooster();
		
			nbCardsInBooster = booster.size();
			currentSelected = player.getSelectedCardId();
			this.panel("RIGHT").removeAll();
			for(int i = 0;i < booster.size() ;i++){
				CardDrawable c = new CardDrawable(booster.get(i),CardDrawable.OVERVIEW_WIDTH,CardDrawable.OVERVIEW_HEIGHT);
				c = new CardHandOnHover(c);
				c = new CardPositionned((i%5)*(c.getWidth()+15), (i/5)*(c.getHeight()+45), c);
				if(this.player.getSelectedCardId() == i)c = new CardSelected(c);
				c = new CardZoomUnderMouse(c);
				c = new CardSelectDraftCard(c);
				
				this.panel("RIGHT").add(c,0);
			}
			this.panel("RIGHT").add(timer);
			
			this.panel("LEFT").removeAll();
			Booster deck = this.player.getDeck();
			for(int i = 0;i < deck.size() ;i--){
				CardDrawable c = new CardDrawable(deck.get(i),CardDrawable.OVERVIEW_WIDTH,CardDrawable.OVERVIEW_HEIGHT);
				c = new CardPositionned((c.getWidth()+2)*(i%2)+1, 15+7*i, c);
				c = new CardZoomUnderMouse(c);
				this.panel("LEFT").add(c,0);
			}
			
			this.panel("TOP").removeAll();
			this.panel("TOP").add(readyContainer);
			
			boolean[] readyStates = this.player.getReadyStates();
			for(int i = 0; i < 8; i++){
				if(readyStates[i]){
					ready[i].setBackground(new Color(0,255,0));
				}else{
					ready[i].setBackground(new Color(255,0,0));
				}
			}
			
			if(!pseudoLoaded ){
				pseudoLoaded = true;
				String[] pseudos = player.getPseudos();
				for(int i = 0; i < 8; i++){
					JLabel jl = new JLabel(pseudos[i]);
					ready[i].add(jl);
				}
			}
	}
	
	public Player getPlayer(){
		return player;
	}
}
