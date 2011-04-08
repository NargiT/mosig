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
		for (PseudoNode pn : childs) {
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
			for (PseudoNode pn : childs) {
				res += pn.id + " ";
			}
		}
		return res;

	}
	
	
	
	
	
}
