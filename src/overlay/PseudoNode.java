package overlay;

import java.util.Collection;
import java.util.LinkedList;

public class PseudoNode {

	private int id;
	private PseudoNode parent;
	private Collection<PseudoNode> childs;
	
	public PseudoNode(int id) {
		super();
		this.id = id;
		parent = null;
		childs = new LinkedList<PseudoNode>();
	}
	
	public void addChild(PseudoNode child) {
		childs.add(child);
	}
	
	public int getNumberOfChilds() {
		int count = 0;
		for (PseudoNode child : childs) {
			count++;
		}
		return count;
	}
	
	public boolean hasParent() {
		return (parent != null);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public PseudoNode getParent() {
		return parent;
	}
	
	public void setParent(PseudoNode parent) {
		this.parent = parent;
	}

	public Collection<PseudoNode> getChilds() {
		return childs;
	}
	
	
	
	
	
}
