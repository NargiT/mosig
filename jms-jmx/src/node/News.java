package node;

import java.io.Serializable;

/**
 * Class used to send throw the JMS API.
 * @author Tchougourian Tigran
 *
 */
public class News implements Serializable {

	/**
	 * Serialisable class
	 */
	private static final long serialVersionUID = -8792474533946691408L;
	
	/**
	 * Id of the source node
	 */
	int from;
	/**
	 * Memory used by the source node
	 */
	long memoryUsed;
	
	/**
	 * Create a news
	 * @param from id of the source node
	 * @param memoryUsed memory used by the source node
	 */
	public News(int from, long memoryUsed) {
		this.from = from;
		this.memoryUsed =  memoryUsed;
	}

	/**
	 * @return the source node identifier
	 */
	public int getFrom() {
		return from;
	}

	/**
	 * @return the memory used by the source node
	 */
	public long getMemoryUsed() {
		return memoryUsed;
	}
	
	

}
