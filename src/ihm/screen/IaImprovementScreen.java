package ihm.screen;

import ia.Datawork;
import ihm.card.IaImprovementCard;

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
		this.panel("DROITE").setBackground(new Color(255,0,255,0));
		
		newChoice();
	}

	private Card card1 = null;
	private Card card2 = null;
	
	public void newChoice() {
		try{
			String refs[] = ia.getRefsWSameSet();
			this.card1 = SingletonBinder.getInstance().getByRef(refs[0]);
			this.card2 = SingletonBinder.getInstance().getByRef(refs[1]);
			this.panel("GAUCHE").removeAll();
			this.panel("GAUCHE").add(new IaImprovementCard(this.card1,this.card2, ia, this));
			this.panel("DROITE").removeAll();
			this.panel("DROITE").add(new IaImprovementCard(this.card2,this.card1, ia, this));
			this.repaint();
		}catch(Exception e){
			newChoice();
		}
	}
}
