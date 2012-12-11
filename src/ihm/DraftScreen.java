package ihm;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import player.Player;

@SuppressWarnings("serial")
public class DraftScreen extends Screen{
	
	private Player player;
	private JLabel timer;
	private JPanel[] ready;
	private JPanel readyContainer;
	private boolean pseudoLoaded = false;
	
	private Thread timerRefreshing;
	
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
		this.panel("RIGHT").add(timer);
		
		this.panel("LEFT").removeAll();
		for(int i = this.player.getDeck().size()-1;i >= 0 ;i--){
			this.panel("LEFT").add(new OverviewCard(this.player.getDeck(), i, this.panel("LEFT")));
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
		return true;
	}
	
}
