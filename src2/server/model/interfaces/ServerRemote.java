package server.model.interfaces;

import client.model.interfaces.ClientRemote;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

import utils.Message;

/**
 * This object allows for a client to use all the features of the chat
 * @author TCHOUGOURIAN Tigran
 */
public interface ServerRemote extends Remote {

    /**
     * Broadcast one message to all the clients connected to the chat.
     * @param msg message to be transimted.
     * @return {@code true} if all the messages were transmited,<br />
     *         {@code false} if at least one failed
     *         </ul>
     * This return value is more as an tips that a real return value.
     * @throws RemoteException
     */
    public boolean broadcast(Message msg) throws RemoteException;

    /**
     * Register a client in to the server, a client must have a unique identifier.
     * @param nickname client's identifier.
     * @param client_stub client's reference of the remote object.
     * @return {@code true} if the client were added, <br />
     * {@code false} if another client has already the same identifier.
     * @throws RemoteException
     */
    public boolean addClient(String nickname, ClientRemote client_stub) throws RemoteException;

    /**
     * Unregister a client from the server using his nickname.
     * @param nickname client's to be removed
     * @return {@code true} if the client were successfully removed, <br />
     *         {@code false} if the nickname is not presents in the server.
     * @throws RemoteException
     */
    public boolean removeClient(String nickname) throws RemoteException;

    /**
     * Return the deserialized history.
     * @return history {@code LinkedList<Message>}
     * @throws RemoteException
     */
    public LinkedList<Message> getHistory() throws RemoteException;
}
