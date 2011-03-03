package client.model.interfaces;

import java.rmi.Remote;

import utils.Message;



public interface ClientRemote extends Remote {
	
	boolean receive(Message msg) throws java.rmi.RemoteException;

}
