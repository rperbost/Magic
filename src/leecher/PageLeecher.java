package leecher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class PageLeecher {
	
	private String pageContent;
	
	public PageLeecher(String target) {
		super();
		this.retrievePage(target);
	}

	private void retrievePage(String target) {
		try{
			pageContent = "";
			URL url = new URL(target);
	        BufferedReader in = new BufferedReader(
	        new InputStreamReader(url.openStream()));
	
	        String inputLine;
	        while ((inputLine = in.readLine()) != null)
	        	pageContent+=inputLine;
	        in.close();
		}catch(IOException e){
			
		}
        
	}

	public String getPageContent() {
		return pageContent;
	}

	
	
	public String toString() {
		return this.getPageContent();
	}
	
}
