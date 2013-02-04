package ihm.screen;

import javax.swing.JPanel;

import binder.Card;
import binder.SingletonBinder;

import ia.Datawork;
import ihm.card.*;

@SuppressWarnings("serial")
public class IaImprovementScreen extends Screen{
	
	public IaImprovementScreen() {
		
		this.init();
		this.refresh();
	}
	
	public void init() {
		this.addPanel("GAUCHE", new JPanel())
		.addPanel("DROITE",new JPanel());
		
		this.panel("GAUCHE").setBounds(0, 0, 400, 600);
		
		this.panel("DROITE").setBounds(400, 0, 400, 600);
	}

	public void refresh() {
		
		String refs[] = new Datawork().getRefsWSameSet();
		Card card1 = SingletonBinder.getInstance().getByRef(refs[0]);
		Card card2 = SingletonBinder.getInstance().getByRef(refs[1]);
		this.panel("GAUCHE").removeAll();
		this.panel("GAUCHE").add(new CardIaImprovement(card2, new CardHandOnHover(new CardPositionned(45, 65, new CardDrawable(card1)))));
		this.panel("DROITE").removeAll();
		this.panel("DROITE").add(new CardIaImprovement(card1, new CardHandOnHover(new CardPositionned(45, 65, new CardDrawable(card2)))));
		
		super.refresh();
	}

}
