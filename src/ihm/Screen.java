package ihm;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

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
		this.add(panel);
		return this;
	}
	public JPanel panel(String name){
		return this.panels.get(name);
	}
	
}
