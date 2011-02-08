/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server;

import chat.client.interfaces.ClientRemote;
import chat.server.interfaces.ServerLocal;
import chat.server.interfaces.ServerRemote;
import chat.utils.Message;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author NargiT
 */
public class Server implements ServerRemote, ServerLocal {

    private HashMap<String, ClientRemote> clients;

    public Server() {
        clients = new HashMap<String, ClientRemote>();
    }

    public boolean add(ClientRemote c) throws RemoteException {

        if (clients.containsKey(c.getNickName())) {
            return false;
        }

        clients.put(c.getNickName(), c);
        return true;
    }

    public void remove(ClientRemote c) throws RemoteException {
        if (clients.containsKey(c.getNickName())) {
            clients.remove(c.getNickName());
        }
    }

    public boolean broadcast(Message msg) throws RemoteException {
        for(Entry<String, ClientRemote> entry : clients.entrySet()) {
            entry.getValue().receive(msg);
        }
        return true;
    }
}
