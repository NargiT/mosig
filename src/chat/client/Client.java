package chat.client;

import chat.client.interfaces.ClientLocal;
import chat.client.interfaces.ClientRemote;
import chat.utils.Message;

public class Client implements ClientLocal, ClientRemote{
	
	private String nickname;
	private int id;
	
	public Client(String nickname) {
		super();
		this.nickname = nickname;
	}

	@Override
	public boolean send(Message msg) {
		// TODO Auto-generated method stub
		
		return true;
		
	}

	@Override
	public boolean register() {
		// TODO Auto-generated method stub
		
		return true;
		
	}

	@Override
	public void unregister() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean receive(Message msg) {
		// TODO Auto-generated method stub
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
