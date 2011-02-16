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

    /**
     * Add a new @Client c to the chat session.
     * @param c, client to add to the chat session
     * @return @true if the client successfully added to the session
     * @return @false if the nickname is already used
     * @throws RemoteException
     */
    public boolean add(String nickname, ClientRemote c)
            throws RemoteException;

    /**
     * Remove an existing @Client c from the chat session.
     * @param c, client to remove from the chat session
     * @throws RemoteException
     */
    public void remove(String nickName)
            throws RemoteException;

    /**
     * Broadcast a message msg to all the clients of the session
     * @param msg, message to be send
     * @return always return @true
     * @throws RemoteException
     */
    public boolean broadcast(Message msg)
            throws RemoteException;
    
}
