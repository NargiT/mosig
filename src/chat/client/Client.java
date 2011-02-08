package chat.client;

import chat.client.interfaces.ClientLocal;
import chat.client.interfaces.ClientRemote;
import chat.utils.Message;

public class Client implements ClientLocal, ClientRemote{
	
	private int id;
	private String nickname;

	@Override
	public void send(Message msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void register() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregister() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receive(Message msg) {
		// TODO Auto-generated method stub
		
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
