package overlay;

import java.util.Collection;
import java.util.LinkedList;

/**
 * 
 * The class VirtualNode is a class representing a node and its basic attributes.
 * It is used for building a virtual tree of nodes, that can be used to start real node processes and give them the correct attributes.
 * 
 * @author Laurent
 * 
 */
public class VirtualNode {

	/**
	 * ID of the node
	 */
	private int id;
	
	/**
	 * Virtual node which is parent to this node
	 */
	private VirtualNode parent;
	
	/**
	 * Virtual nodes which are a child of this node
	 */
	private Collection<VirtualNode> childs;
	
	/**
	 * Constructor of the virtual node
	 * 
	 * @param id
	 * 			  id of the node
	 * 
	 */
	public VirtualNode(int id) {
		super();
		this.id = id;
		parent = null;
		childs = new LinkedList<VirtualNode>();
	}
	
	/**
	 * Adds a virtual node to the list of childs
	 * 
	 * @param child
	 * 			  virtual node that should be a child
	 */
	public void addChild(VirtualNode child) {
		childs.add(child);
	}
	
	/**
	 * Returns the number of childs
	 * 
	 * @return the number of childs
	 */	
	public int getNumberOfChilds() {
		return childs.size();
	}
	
	/**
	 * Checks if the node has a parent
	 * 
	 * @return true if the node has a parent, false otherwise
	 */	
	public boolean hasParent() {
		return (parent != null);
	}
	
	/**
	 * Getter-method for the id of the node
	 * 
	 * @return ID of the node
	 */	
	public int getId() {
		return id;
	}
	
	/**
	 * Setter-method for the id of the node
	 * 
	 * @param id
	 * 			  id of the node
	 */	
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter-method for the parent of the node
	 * 
	 * @return parent which is parent to this node
	 */	
	public VirtualNode getParent() {
		return parent;
	}
	
	/**
	 * Setter-method for the parent of the node
	 * 
	 * @param parent
	 * 			  Virtual node that should be a parent of this node
	 * 
	 */	
	public void setParent(VirtualNode parent) {
		this.parent = parent;
	}

	/**
	 * Getter-method for the list of childs
	 * 
	 * @return collection of childs
	 */	
	public Collection<VirtualNode> getChilds() {
		return childs;
	}
	
	/**
	 * To-String method.
	 * 
	 * Gathers the information of this node and tries to represent it in a string
	 * 
	 * @return String yielding the information about the node
	 */	
	public String toString() {
		String res = new String();
		res += "ID="+ id + "; ";
		if (parent != null) {
			res += "Parent=" + parent.getId() + "; ";
		} else {
			res += "Parent=NULL; ";
		}
		res += ("Childs: ");
		if (childs.isEmpty()) {
			res += "No childs";
		} else {
			for (VirtualNode pn : childs) {
				res += pn.id + " ";
			}
		}
		return res;

	}
	
	
	
	
	
}
