package chat.client.interfaces;

import chat.utils.Message;

public interface ClientRemote {

	void receive(Message msg);
	
}
