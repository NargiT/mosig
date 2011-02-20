package server.model.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

import utils.Message;



public interface ServerRemote extends Remote {

	public boolean broadcast(Message msg) throws RemoteException;
	
	public boolean addClient(String nickname) throws RemoteException;
	
	public boolean removeClient(String nickname) throws RemoteException;
	
	public LinkedList<Message> getHistory() throws RemoteException;
	
}
