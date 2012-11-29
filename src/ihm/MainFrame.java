package ihm;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	private static MainFrame theInstance= null;
	public static MainFrame getInstance(){
		if(theInstance == null){
			theInstance = new MainFrame();
		}
		return theInstance;
	}
	
	private Map<String,Screen> screens;
	private RefreshingThread refreshingThread;
	private String actualScreen;
	
	private MainFrame(){
		this.setSize(800, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.screens = new HashMap<String,Screen>();
		this.actualScreen = "";
		this.refreshingThread = new RefreshingThread(this);
		this.refreshingThread.start();
	}
	
	public MainFrame activeScreen(String name){
		if(this.screens.containsKey(name)){
			if(!actualScreen.equals(""))screens.get(actualScreen).setVisible(true);
			screens.get(name).setVisible(true);
			this.actualScreen = name;
		}
		return this;
	}
	
	public MainFrame addScreen(String name,Screen screen){
		this.screens.put(name,screen);
		this.add(screen);
		screen.setVisible(false);
		return this;
	}
	
	public void repaint(){
		if(!actualScreen.equals("")){
			//System.out.println(actualScreen);
			screens.get(actualScreen).repaint();
		}
		super.repaint();
	}
	
}
