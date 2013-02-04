package ihm.screen;

import ia.Datawork;
import ihm.card.*;

import java.awt.Color;

import javax.swing.JPanel;

import binder.Card;
import binder.Deck;
import binder.SingletonBinder;

@SuppressWarnings("serial")
public class IaImprovementScreen extends Screen{

	Datawork ia;
	
	public IaImprovementScreen(){
		ia = new Datawork();
		this.addPanel("GAUCHE", new JPanel())
		.addPanel("DROITE",new JPanel());
		
		this.panel("GAUCHE").setBounds(0, 0, 400, 600);
		//this.panel("GAUCHE").setBackground(new Color(255,255,0));
		
		this.panel("DROITE").setBounds(400, 0, 400, 600);
		//this.panel("DROITE").setBackground(new Color(255,0,255,0));
		
		newChoice();
	}

	
	
	public void newChoice() {
		try{
			String refs[] = ia.getRefsWSameSet();
			Card card1 = SingletonBinder.getInstance().getByRef(refs[0]);
			Card card2 = SingletonBinder.getInstance().getByRef(refs[1]);
			this.panel("GAUCHE").removeAll();
			this.panel("GAUCHE").add(new CardIaImprovement(card2, new CardHandOnHover(new CardPositionned(45, 65, new CardDrawable(card1)))));
			this.panel("DROITE").removeAll();
			this.panel("DROITE").add(new CardIaImprovement(card1, new CardHandOnHover(new CardPositionned(45, 65, new CardDrawable(card2)))));
			
			this.repaint();
		}catch(Exception e){
			//e.printStackTrace();
			newChoice();
		}
	}
}
