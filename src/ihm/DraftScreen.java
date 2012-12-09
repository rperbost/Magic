package ihm;

import java.awt.Color;

import javax.swing.JPanel;

import player.Player;

@SuppressWarnings("serial")
public class DraftScreen extends Screen{
	
	private Player player;
	
	public DraftScreen(){
		this.addPanel("LEFT", new JPanel())
			.addPanel("TOP",new JPanel())
			.addPanel("RIGHT",new JPanel());
		
		this.panel("LEFT").setBounds(0, 0, 200, 600);
		this.panel("LEFT").setBackground(new Color(255,255,0));
		
		this.panel("TOP").setBounds(200, 0, 600, 50);
		this.panel("TOP").setBackground(new Color(0,0,255));
		
		this.panel("RIGHT").setBounds(200, 50, 600, 750);
		this.panel("RIGHT").setBackground(new Color(0,255,255));
		this.panel("RIGHT").setLayout(null);
		
		this.player = null;
	}
	
	public Screen setPlayer(Player p){
		this.player = p;
		return this;
	}
	
	public void repaint(){
		if(player != null){
			//System.out.println(player.getTimer());
			this.refreshRight();
		}
		super.repaint();
	}

	private void refreshRight() {
		if(this.player.getBooster() == null)return;
		this.panel("RIGHT").removeAll();
		for(int i = 0;i < this.player.getBooster().size() ;i++){
			ClickableCard card = null;
			
			this.panel("RIGHT").add(ClickableCardFactory.getClickableCard(this.player,i,this.panel("RIGHT")));
		}
	}
	
}
