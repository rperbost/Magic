package ihm.screen;

import java.awt.Color;

import javax.swing.JLabel;
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
		.addPanel("TOP", new JPanel())
		.addPanel("DROITE",new JPanel());
		
		this.panel("GAUCHE").setBounds(0, 20, 400, 580);
		
		this.panel("DROITE").setBounds(400, 20, 400, 580);
		
		this.panel("TOP").setBounds(0,0,800,20);
		this.panel("TOP").setBackground(Color.WHITE);
		this.panel("TOP").setOpaque(true);
		JLabel jl =new JLabel();
		jl.setSize(800,20);
		jl.setHorizontalAlignment(0);
		jl.setText("Aidez-nous à améliorer ce logiciel en choisissant la meilleure carte.");
		this.panel("TOP").add(jl,0);
	}

	public void refresh() {
		try{
			String refs[] = new Datawork().getRefsWSameSet();
			Card card1 = SingletonBinder.getInstance().getByRef(refs[0]);
			Card card2 = SingletonBinder.getInstance().getByRef(refs[1]);
			this.panel("GAUCHE").removeAll();
			this.panel("GAUCHE").add(new CardIaImprovement(card2, new CardHandOnHover(new CardPositionned(45, 65, new CardDrawable(card1)))));
			this.panel("DROITE").removeAll();
			this.panel("DROITE").add(new CardIaImprovement(card1, new CardHandOnHover(new CardPositionned(45, 65, new CardDrawable(card2)))));
			super.refresh();
		}catch(Exception e){
			this.refresh();
		}
		
	}

}
