package node;

import java.io.Serializable;

public class News implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8792474533946691408L;
	
	int from;
	long memoryUsed;
	
	public News(int from, long memoryUsed) {
		this.from = from;
		this.memoryUsed =  memoryUsed;
	}

	public int getFrom() {
		return from;
	}

	public long getMemoryUsed() {
		return memoryUsed;
	}
	
	

}
