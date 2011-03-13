package client.model.interfaces;

import java.rmi.Remote;

import utils.Message;



public interface ClientRemote extends Remote {
	
	/**
	 * 
	 * Depending of the nature of the client the message is printed on the console or displayed on the gui of the client.
	 * This method is used by the server to send a message to a remote client-object.
	 * 
	 * @param msg
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	boolean receive(Message msg) throws java.rmi.RemoteException;

}
