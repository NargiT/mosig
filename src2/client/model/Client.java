package client.model;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import server.model.interfaces.ServerRemote;
import utils.Message;
import utils.Properties;

import client.controller.Controller;
import client.model.interfaces.ClientLocal;
import client.model.interfaces.ClientRemote;
import client.run.console.StartConsoleClient;



public class Client implements ClientLocal, ClientRemote {

	private ServerRemote server;
	private String nickname;
	private boolean connected = false;
	private Controller controller;
	private String type;
	private Registry registry;
	
	public String getType() throws RemoteException {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Client(String nickname, String type, Controller controller) {
		super();
		this.controller = controller;
		this.setNickname(nickname);
		this.setType(type);
		initialize();
	}
	
	public void initialize() {
		System.setProperty("java.rmi.server.codebase",
                ClientRemote.class.getProtectionDomain().getCodeSource().getLocation().toString());
		try {
			registry = LocateRegistry.getRegistry();
			server = (ServerRemote) registry.lookup(Properties.SERVER_NAME);
			
			ClientRemote client_stub = (ClientRemote) UnicastRemoteObject.exportObject(this,0);
			registry.bind(nickname,  client_stub);
			register();
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Client already Connected");
			if (type.equals("GUI")) {
				controller.throwErrorMessage("Client already Connected");
			}
			//e.printStackTrace();
		}
	}

	@Override
	public boolean receive(Message msg) throws RemoteException {
		System.out.print("[" + msg.getDate() + "] " + msg.getFrom() + ": " + msg.getText() + "\n");
		if (this.getType() == "GUI") {
			controller.displayMessage(msg);
		}
		return true;
	}

	@Override
	public boolean register() {
		try {
			Logger.getLogger(Client.class.getName()).log(Level.INFO, "Nickname = " + this.getNickName());
			server.addClient(nickname);
			connected = true;
		} catch(RemoteException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return false;
		} 
			
		return true;
	}

	@Override
	public boolean unregister() {
		try {
			server.removeClient(nickname);
			
			registry.unbind(nickname);
			
			connected = false;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean send(String text) {
		Message msg = new Message(text, nickname);
		try {
			server.broadcast(msg);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public String getNickName() {
		// TODO Auto-generated method stub
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public boolean printHistory() {
		LinkedList<Message> history;
		try {
			history = server.getHistory();
			for (Message m : history) {
				receive(m);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean isConnected() {
		return connected;
	}

}
