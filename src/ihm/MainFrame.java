package ihm;

import ihm.screen.Screen;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	private static MainFrame theInstance= null;
	public static MainFrame getInstance(){
		if(theInstance == null){
			theInstance = new MainFrame();
		}
		return theInstance;
	}
	
	private Map<String,Screen> screens;
	private String actualScreen;
	
	private MainFrame(){
		this.setSize(800, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel backgroundImage = new JLabel();
		backgroundImage.setBounds(0, 0, 800, 600);
		backgroundImage.setIcon(new ImageIcon("pics/background.jpg"));
		this.add(backgroundImage);
		
		this.screens = new HashMap<String,Screen>();
		this.actualScreen = "";
		
		this.setResizable(false);
	}
	
	public MainFrame activeScreen(String name){
		if(this.screens.containsKey(name)){
			if(!actualScreen.equals(""))screens.get(actualScreen).setVisible(false);
			screens.get(name).setVisible(true);
			screens.get(name).repaint();
			this.actualScreen = name;
		}
		return this;
	}
	
	public MainFrame addScreen(String name,Screen screen){
		this.screens.put(name,screen);
		this.add(screen,0);
		screen.setVisible(false);
		return this;
	}
	
	public void repaint(){
		try{
			if(!actualScreen.equals("")){
				screens.get(actualScreen).repaint();
			}
			super.repaint();
		}catch(Exception e){
		}
	}
	public Screen getScreen(){
		if(!actualScreen.equals("")){
			return screens.get(actualScreen);
		}
		return null;
	}
}
