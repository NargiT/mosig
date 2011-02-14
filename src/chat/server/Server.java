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
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NargiT
 */
public class Server implements ServerRemote, ServerLocal {

    private HashMap<String, ClientRemote> clients;

    public Server() {
        clients = new HashMap<String, ClientRemote>();
    }

    @Override
    public boolean add(ClientRemote c) throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(Constants.HOST);
            ClientRemote cr = (ClientRemote) registry.lookup(c.getNickname());
            if (clients.containsKey(cr.getNickname())) {
                return false;
            }
            clients.put(c.getNickname(), cr);
        } catch (NotBoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AccessException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public void remove(ClientRemote c) throws RemoteException {
        if (clients.containsKey(c.getNickname())) {
            clients.remove(c.getNickname());
        }
    }

    @Override
    public boolean broadcast(Message msg) throws RemoteException {
        for (Entry<String, ClientRemote> entry : clients.entrySet()) {
            entry.getValue().receive(msg);
        }
        return true;
    }
}
