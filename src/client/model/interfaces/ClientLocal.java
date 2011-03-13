package client.model.interfaces;

/**
 * Interface for the client itself to interact with the Server
 *
 */
public interface ClientLocal {
	
	
	/**
	 * Registers the client on the server
	 * Uses remote server-object to add itself (reduced to the methods listed in the remote-interface) to the client-list.
	 * @return
	 */
	boolean register();
	
	/**
	 * Unregisters the client from the server
	 * Uses remote server-object to remove itself from the client-list.
	 * @return
	 */
	boolean unregister();
	
	/**
	 * Sends a String as message to all the clients connected to the server.
	 * Creates as message object from the String.
	 * Uses remote server-object to broadcast this message to the other clients.
	 * @param text
	 * @return
	 */
	boolean send(String text);
	
	/**
	 * Makes client receive all the messages that were sent to the server before.
	 * Uses remote server-object to get a list of the messages.
	 * Makes the local client receive each of these messages.
	 * @return
	 */
	boolean printHistory();
	
	/**
	 * Checks if the client is connected to the server.
	 * When client connects/disconnects to a server, a boolean variable is set true/false,
	 * this variable is checked by this method.
	 *  
	 * @return
	 */
	boolean isConnected();
	
	/**
	 * Getter-Method for the nickname of the client.
	 * @return
	 */
	String getNickname();
	
	/**
	 * Setter-Method for the nickname of the client.
	 * @param nickname
	 */
    void setNickname(String nickname);
	
	
}
