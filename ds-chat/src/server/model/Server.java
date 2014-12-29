package server.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import server.model.interfaces.ServerRemote;
import utils.Message;
import utils.Properties;
import utils.XMLTools;

import client.model.interfaces.ClientRemote;

/**
 * This class allows for a client to use all the features of the chat
 * @author TCHOUGOURIAN Tigran
 */
public class Server implements ServerRemote {

    /**
     * Map of all the clients registered to the chat,
     * the nickname identify the client in the set.
     */
    private HashMap<String, ClientRemote> clients;

    /**
     * List of all messaged stored in the chat since the first day.
     */
    private LinkedList<Message> history;

    /**
     * Constructor of the server, initialize the client map and load the
     * history if it's available otherwise create a new one.
     */
    public Server() {
        clients = new HashMap<String, ClientRemote>();

        try {
            history = (LinkedList<Message>) XMLTools.decodeFromFile(Properties.HISTORY_FILE);

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            history = new LinkedList<Message>();

        } catch (IOException e) {
            //e.printStackTrace();
            history = new LinkedList<Message>();
        }

    }

    /**
     * Broadcast one message to all the clients connected to the chat.
     * @param msg message to be transimted.
     * @return {@code true} if all the messages were transmited,<br />
     *         {@code false} if at least one failed
     *         </ul>
     * This return value is more as an tips that a real return value.
     * @throws RemoteException
     */
    @Override
    public boolean broadcast(Message msg) throws RemoteException {
        boolean toReturn = true;
        for (ClientRemote c : clients.values()) {
            toReturn = toReturn && c.receive(msg);
        }
        history.add(msg);
        try {
            XMLTools.encodeToFile(history, Properties.HISTORY_FILE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

     /**
     * Register a client in to the server, a client must have a unique identifier.
     * @param nickname client's identifier.
     * @param client_stub client's reference of the remote object.
     * @return {@code true} if the client were added, <br />
     * {@code false} if another client has already the same identifier.
     * @throws RemoteException
     */
    @Override
    public boolean addClient(String nickname, ClientRemote client_stub) throws RemoteException {
        ClientRemote client = (ClientRemote) client_stub;
        if (clients.containsKey(nickname)) {
            return false;
        }
        clients.put(nickname, client);
        Message msg = new Message("User " + nickname + " joined the chat!", "SERVER");
        broadcast(msg);
        Logger.getLogger(Server.class.getName()).log(Level.INFO, "Registered user " + nickname);
        return true;
    }

    /**
     * Unregister a client from the server using his nickname.
     * @param nickname client's to be removed
     * @return {@code true} if the client were successfully removed, <br />
     *         {@code false} if the nickname is not presents in the server.
     * @throws RemoteException
     */
    @Override
    public boolean removeClient(String nickname) throws RemoteException {
        if (clients.containsKey(nickname)) {
            clients.remove(nickname);
            Message msg = new Message("User " + nickname + " left the chat", "SERVER");
            broadcast(msg);
            Logger.getLogger(Server.class.getName()).log(Level.INFO, "Unregistered user " + nickname);
            return true;
        }
        return false;
    }

    /**
     * Return the deserialized history.
     * @return history {@code LinkedList<Message>}
     * @throws RemoteException
     */
    @Override
    public LinkedList<Message> getHistory() throws RemoteException {
        return history;
    }
}
