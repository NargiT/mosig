package server.model;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import server.model.interfaces.ServerRemote;
import utils.Properties;


/**
 * Main Server class which start the server
 * The RMI registery should be started before
 * to run this class.
 */
public class StartServer {
	//-Djava.rmi.server.hostname=129.88.254.49
	public static void main(String[] args) {
		try {
			System.setProperty("java.rmi.server.codebase",
                    ServerRemote.class.getProtectionDomain().getCodeSource().getLocation().toString());

			//Create Server RemoteObject
			Server server = new Server();
			ServerRemote server_stub = (ServerRemote) UnicastRemoteObject.exportObject(server,0);
			
			//Register RemoteObject in RMI-Registry
			Registry registry = LocateRegistry.getRegistry(args[0]);
			registry.bind(Properties.SERVER_NAME,  server_stub);
			
			System.out.println("ChatServer ready");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
