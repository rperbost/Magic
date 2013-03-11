package launcher;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import leecher.Extractor;

import server.Server;

public class MagicServer {
	public static void main(String[] args) throws RemoteException{
		System.out.println("Demarrage du serveur");
		
		try {
			Extractor diggingInformations = new Extractor();
			diggingInformations.execute();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
		try {
			Naming.rebind("//localhost:2020/MagicServer", Server.getInstance() );
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
				
	}
}
