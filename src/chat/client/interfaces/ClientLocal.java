package chat.client.interfaces;

import chat.utils.Message;

public interface ClientLocal {

	boolean send(Message msg);
	boolean register();
	void unregister();
        String getNickname();
}
