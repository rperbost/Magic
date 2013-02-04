package server;

import ihm.card.*;
import ihm.screen.Screen;

import java.awt.Color;

import javax.swing.JPanel;

import binder.Card;
import binder.Deck;
import binder.SingletonBinder;


public class TestScreen extends Screen {
	
	public TestScreen(){
		this.addPanel("t", new JPanel());
		this.panel("t").setBounds(0, 0, 800, 600);
		this.panel("t").setBackground(new Color(255,255,0));
		Card c = SingletonBinder.getInstance().get(100);
		CardDrawable cd = new CardDrawable(c,100,142);
		this.panel("t").add(new CardDraggableDeck(new Deck(),new CardPositionned(50, 50,new CardDraggableDeck(new Deck(), new CardZoomUnderMouse(new CardBumpOnHover(new CardHandOnHover(new CardSelected(new CardDrawable(c)))))))));
	}

}
