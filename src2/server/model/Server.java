package server.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import server.model.interfaces.ServerRemote;
import utils.Message;
import utils.Properties;
import utils.XMLTools;

import client.model.interfaces.ClientRemote;



public class Server implements ServerRemote {

	private HashMap<String, ClientRemote> clients;
	private LinkedList<Message> history;
	
	public Server() {
		
		clients = new HashMap<String, ClientRemote>();
		
		try {
			history = (LinkedList<Message>) XMLTools.decodeFromFile(Properties.HISTORY_FILE);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			history = new LinkedList<Message>();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			history = new LinkedList<Message>();
		}

		
	}

	@Override
	public boolean broadcast(Message msg) throws RemoteException {
		
		for (ClientRemote c : clients.values()) {
			c.receive(msg);
		}
		history.add(msg);
		try {
			XMLTools.encodeToFile(history, Properties.HISTORY_FILE);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public boolean addClient(String nickname) throws RemoteException {
		Registry registry = LocateRegistry.getRegistry();
		try {
			Logger.getLogger(Server.class.getName()).log(Level.INFO, "Trying to register user " + nickname);
			ClientRemote client = (ClientRemote) registry.lookup(nickname);
			clients.put(nickname, client);
			Message msg = new Message("User " + nickname + " joined the chat!","SERVER");
			broadcast(msg);
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean removeClient(String nickname) throws RemoteException {
		if (clients.containsKey(nickname)) {
			clients.remove(nickname);
			Message msg = new Message("User " + nickname + " left the chat","SERVER");
			broadcast(msg);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public LinkedList<Message> getHistory() throws RemoteException {
		return history;
	}
}
