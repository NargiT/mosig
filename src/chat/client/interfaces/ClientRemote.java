package chat.client.interfaces;

import chat.utils.Message;
import java.rmi.Remote;

public interface ClientRemote extends Remote {

	boolean receive(Message msg) throws java.rmi.RemoteException;
        String getNickname() throws java.rmi.RemoteException;
	
}
