package chat.client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import chat.client.interfaces.ClientLocal;
import chat.client.interfaces.ClientRemote;
import chat.server.Server;
import chat.utils.Constants;
import chat.utils.Message;

public class Client implements ClientLocal, ClientRemote{
	
	private String nickname;
	private int id;
	private Server server;
	
	public Client(String nickname) {
		super();
		this.nickname = nickname;
		initialize();
		
	}
	
	public void initialize() {
		try {
			Registry registry = LocateRegistry.getRegistry(Constants.HOST);
			server = (Server) registry.lookup(Constants.SERVER);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean send(Message msg) {
		// TODO Auto-generated method stub
		server.broadcast(msg);
		return true;
		
	}

	@Override
	public boolean register() {
		// TODO Auto-generated method stub
		server.add(this);
		return true;
		
	}

	@Override
	public void unregister() {
		// TODO Auto-generated method stub
		server.remove(this);
		
	}

	@Override
	public boolean receive(Message msg) {
		// TODO Auto-generated method stub
		System.out.println(msg.getText());
		return true;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
