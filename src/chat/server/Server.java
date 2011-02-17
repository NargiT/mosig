/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server;

import chat.utils.Constants;
import chat.client.interfaces.ClientRemote;
import chat.server.interfaces.ServerLocal;
import chat.server.interfaces.ServerRemote;
import chat.utils.Message;
import chat.utils.XMLTools;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TCHOUGOURIAN Tigran
 */
public class Server implements ServerRemote, ServerLocal {

    private HashMap<String, ClientRemote> clients;
    private Registry registry;
    private LinkedList<Message> history;

    public Server() throws RemoteException {
        registry = LocateRegistry.getRegistry();
        clients = new HashMap<String, ClientRemote>();
        try {
            history = (LinkedList<Message>) XMLTools.decodeFromFile(Constants.HISTORYFILE);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            history = new LinkedList<Message>();
        } catch (IOException ex) {
            //Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            history = new LinkedList<Message>();
        }
    }

    @Override
    public boolean add(String nickName) throws RemoteException {
        try {
            Registry clientRegistry = LocateRegistry.getRegistry(Constants.HOST);
            ClientRemote cr = (ClientRemote) clientRegistry.lookup(nickName);
            if (clients.containsKey(nickName)) {
                return false;
            }
            clients.put(cr.getNickname(), cr);
            if (!history.isEmpty()) {
                cr.receive(history);
            }
        } catch (NotBoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AccessException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public void remove(String nickName) throws RemoteException, AccessException {
        if (clients.containsKey(nickName)) {
            clients.remove(nickName);
            try {
                registry.unbind(nickName);
            } catch (NotBoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean broadcast(Message msg) throws RemoteException {
        history.add(msg);
        
        for (Entry<String, ClientRemote> entry : clients.entrySet()) {
            entry.getValue().receive(msg);
        }
        try {
            XMLTools.encodeToFile(history, Constants.HISTORYFILE);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public Registry getRegistry() {
        return registry;
    }
}
