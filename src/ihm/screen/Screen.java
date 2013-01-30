package ihm.screen;

import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;
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
		this.setOpaque(false);
	}
	public Screen addPanel(String name,JPanel panel){
		this.panels.put(name,panel);
		panel.setName(name);
		panel.setVisible(true);
		panel.setLayout(null);
		panel.setOpaque(false);
		this.add(panel);
		return this;
	}
	public JPanel panel(String name){
		return this.panels.get(name);
	}
	
	public void repaint(){
		super.repaint();
	}
	public String pointIsOnByScreen(Point p) {
		
		double x = p.getX()-this.getLocationOnScreen().getX();
		double y = p.getY()-this.getLocationOnScreen().getY();
		
		Iterator<String> it = panels.keySet().iterator();
		
		while(it.hasNext()){
			String panel_name = it.next();
			JPanel panel = panels.get(panel_name);
			if( panel.getBounds().contains(x,y) ){
				return panel_name;
			}
		}
		
		return null;
	}
}
