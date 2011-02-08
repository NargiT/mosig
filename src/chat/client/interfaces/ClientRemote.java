package chat.client.interfaces;

import chat.utils.Message;

public interface ClientRemote extends java.rmi.Remote {

	boolean receive(Message msg) throws java.rmi.RemoteException;
	
}
