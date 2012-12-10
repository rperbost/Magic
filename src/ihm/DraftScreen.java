package ihm;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import player.Player;

@SuppressWarnings("serial")
public class DraftScreen extends Screen{
	
	private Player player;
	
	public DraftScreen(){		
		this.addPanel("LEFT", new JPanel())
			.addPanel("TOP",new JPanel())
			.addPanel("RIGHT",new JPanel());
		
		this.panel("LEFT").setBounds(0, 0, 204, 600);
		this.panel("LEFT").setBackground(new Color(255,255,0));
		
		this.panel("TOP").setBounds(204, 0, 596, 50);
		this.panel("TOP").setBackground(new Color(0,0,255));
		
		this.panel("RIGHT").setBounds(204, 50, 596, 750);
		this.panel("RIGHT").setBackground(new Color(0,255,255));
		
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

	private boolean refreshRight() {
		if(this.player.getBooster() == null)return false;
		this.panel("RIGHT").removeAll();
		for(int i = 0;i < this.player.getBooster().size() ;i++){
			this.panel("RIGHT").add(ClickableCardFactory.getClickableCard(this.player,i,this.panel("RIGHT")));
		}
		
		this.panel("LEFT").removeAll();
		for(int i = this.player.getDeck().size()-1;i >= 0 ;i--){
			this.panel("LEFT").add(new OverviewCard(this.player.getDeck(), i, this.panel("LEFT")));
		}
		
		return true;
		
	}
	
}
