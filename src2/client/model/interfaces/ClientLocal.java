package client.model.interfaces;

public interface ClientLocal {

	boolean register();
	boolean unregister();
	boolean send(String text);
	boolean printHistory();
	boolean isConnected();
	String getNickName();
	
	
}
