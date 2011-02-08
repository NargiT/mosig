/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.server.interfaces;

import chat.client.interfaces.ClientRemote;
import chat.utils.Message;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author TCHOUGOURIAN Tigran
 */
public interface ServerRemote extends Remote {

    public boolean add(ClientRemote c)
            throws RemoteException;

    public void remove(ClientRemote c)
            throws RemoteException;

    public boolean broadcast(Message msg)
            throws RemoteException;
    
}
