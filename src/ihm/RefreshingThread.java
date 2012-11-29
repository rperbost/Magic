package ihm;

import java.awt.Component;

public class RefreshingThread extends Thread{

	private Component componentThatNeedRepaint;
	private boolean alive;
	public RefreshingThread(Component c){
		componentThatNeedRepaint = c;
		alive = true;
	}

	public void run() {
		while(alive){
			try {
				componentThatNeedRepaint.repaint();
				//System.out.println(componentThatNeedRepaint.getName());
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void kill() {
		
		alive = false;
		
	}
}
