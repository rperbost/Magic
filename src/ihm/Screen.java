package ihm;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import player.Player;

@SuppressWarnings("serial")
public abstract class Screen extends JPanel{
	Map <String,JPanel> panels;
	public Screen(){
		this.panels = new HashMap<String,JPanel>();
		this.setVisible(true);
		this.setSize(800, 600);
		this.setLayout(null);
	}
	public Screen addPanel(String name,JPanel panel){
		this.panels.put(name,panel);
		panel.setVisible(true);
		panel.setLayout(null);
		this.add(panel);
		return this;
	}
	public JPanel panel(String name){
		return this.panels.get(name);
	}
	
	public void repaint(){
		super.repaint();
	}
}
