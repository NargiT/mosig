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

public class Server implements ServerRemote {

    private HashMap<String, ClientRemote> clients;
    private LinkedList<Message> history;

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

    @Override
    public LinkedList<Message> getHistory() throws RemoteException {
        return history;
    }
}
