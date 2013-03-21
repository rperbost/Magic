package leecher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class PageRetriever {
	
	private String pageContent;
	
	public PageRetriever(String target) {
		super();
		this.retrievePage(target);
	}

	public String getPageContent() {
		return pageContent;
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

	
	
	public String toString() {
		return this.getPageContent();
	}
	
}
