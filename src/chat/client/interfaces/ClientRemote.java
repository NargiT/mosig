package chat.client.interfaces;

import chat.utils.Message;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

public interface ClientRemote extends Remote {

	boolean receive(Message msg) throws RemoteException;
        String getNickname() throws RemoteException;
        boolean receive(LinkedList<Message> history) throws RemoteException;
	
}
