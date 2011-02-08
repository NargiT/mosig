package chat.client.interfaces;

import chat.utils.Message;

public interface ClientRemote {

	boolean receive(Message msg);
	
}
