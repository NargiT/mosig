package client.model;

import java.rmi.AccessException;
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

/**
 * 
 * Model of the client used by the controller to communicate with the View.
 * Init
 *
 */
public class Client implements ClientLocal, ClientRemote {

    private ServerRemote server;
    private String nickname;
    private boolean connected = false;
    private Controller controller;
    private String type;
    private Registry registry;

    /**
     * 
     * Getter-Method for the type of the client
     * 
     * @return
     * @throws RemoteException
     */
    public String getType() throws RemoteException {
        return type;
    }

    /**
     * Setter-Method for the type of the Client.
     * Type can be either a GUI or Console.
     * This important to know if the messages should be printed on the screen or displayed in the gui.
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * Constructor of the client.
     * Sets the properties according to the parameters given.
     * Then calls initialize();
     * 
     * @param nickname
     * @param type
     * @param controller
     */
    public Client(String nickname, String type, Controller controller) {
        super();
        this.controller = controller;
        this.nickname = nickname;
        this.type = type;
        initialize();
    }

    /**
     * 
     * Initializes the registry and gets a remote-object of the server.
     * 
     */
    public void initialize() {
        try {
            System.setProperty("java.rmi.server.codebase", ClientRemote.class.getProtectionDomain().getCodeSource().getLocation().toString());
            registry = LocateRegistry.getRegistry();
            server = (ServerRemote) registry.lookup(Properties.SERVER_NAME);
        } catch (NotBoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AccessException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	/**
	 * 
	 * Depending of the nature of the client the message is printed on the console or displayed on the gui of the client.
	 * This method is used by the server to send a message to a remote client-object.
	 * 
	 * @param msg
	 * @return
	 * @throws java.rmi.RemoteException
	 */
    @Override
    public boolean receive(Message msg) throws RemoteException {
        if (this.getType().equals("GUI")) {
            controller.displayMessage(msg);
        } else {
            System.out.print("[" + msg.getDate() + "] " + msg.getFrom() + ": " + msg.getText() + "\n");
        }
        return true;
    }
    
	/**
	 * Registers the client on the server. 
	 * Uses remote server-object to add itself (reduced to the methods listed in the remote-interface) to the client-list.
	 * @return
	 */
    @Override
    public boolean register() {
        connected = false;
        try {
            ClientRemote client_stub = (ClientRemote) UnicastRemoteObject.exportObject(this, 0);
            while (!connected) {
                connected = server.addClient(nickname, client_stub);
                if (connected == false) {
                    if (type.equals("GUI")) {
                        controller.throwErrorMessage("Client already Connected");
                    } else {
                        System.out.println("Client already Connected");
                    }
                }
            }
        } catch (RemoteException e) {
            return false;
        }
        return true;
    }

	/**
	 * Unregisters the client from the server
	 * Uses remote server-object to remove itself from the client-list.
	 * @return
	 */
    @Override
    public boolean unregister() {
        try {
            server.removeClient(nickname);
            //registry.unbind(nickname);
            connected = false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

	/**
	 * Sends a String as message to all the clients connected to the server.
	 * Creates as message object from the String.
	 * Uses remote server-object to broadcast this message to the other clients.
	 * @param text
	 * @return
	 */
    @Override
    public boolean send(String text) {
        Message msg = new Message(text, nickname);
        try {
            server.broadcast(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

	/**
	 * Getter-Method for the nickname of the client.
	 * @return
	 */
    @Override
    public String getNickname() {
        return nickname;
    }

	/**
	 * Setter-Method for the nickname of the client.
	 * @param nickname
	 */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

	/**
	 * Makes client receive all the messages that were sent to the server before.
	 * Uses remote server-object to get a list of the messages.
	 * Makes the local client receive each of these messages.
	 * @return
	 */
    @Override
    public boolean printHistory() {
        LinkedList<Message> history;
        try {
            history = server.getHistory();
            for (Message m : history) {
                receive(m);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

	/**
	 * Checks if the client is connected to the server.
	 * When client connects/disconnects to a server, a boolean variable is set true/false,
	 * this variable is checked by this method.
	 *  
	 * @return
	 */
    @Override
    public boolean isConnected() {
        return connected;
    }
    
}
