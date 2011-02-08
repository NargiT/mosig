package chat.client.interfaces;

import chat.utils.Message;

public interface ClientLocal {

	void send(Message msg);
	void register();
	void unregister();
	
}
