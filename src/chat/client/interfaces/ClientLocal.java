package chat.client.interfaces;

public interface ClientLocal {

	boolean send(String msg);
	boolean register();
	void unregister();
        String getNickname();
}
