package overlay;

import java.io.IOException;
import java.util.Collection;

public class Overlaycreator {

	public static void main(String[] args) {
		 
		if (args.length != 4) {
			System.out.println("Number of arguments is wrong");
		}
		
		int n = readNumber(args[1]);
		int value = readNumber(args[3]);
		if (n == -1 || value == -1) {
			return;
		}
		
		String mode;
		if (args[2].contains("a")) {
			mode = "arity";
		} else {
			mode = "depth";
		}
		
		if (mode.equals("arity")) {
			System.out.println("Creating NodeTree of " + n + " nodes with arity " + value);
			createTreeWithArity(n, value);
		} else {
			System.out.println("Creating NodeTree of " + n + " nodes with depth " + value);
			createTreeWithDepth(n, value);
		}
	}
	 
	public static void createTreeWithArity(int n, int arity) {
		
		System.out.println("Tree will have depth " + getDepth(n,arity));
		return;
		/**
		int count = 1;
		PseudoNode pn;
		PseudoNode master = null;
		int depth = 0;
		while (count <= n) {
			pn = new PseudoNode(count);
			
			if (count == 1) {
				master = pn;
				depth++;
				continue;
			}
			
			PseudoNode root = master;
			while (true) {
				
			}
			for (PseudoNode child : master.getChilds()) {
				
			}

			count++;
			
		}
		**/
	}
	
	public static void createTreeWithDepth(int n, int depth) {
		System.out.println("Tree will have arity " + getArity(n,depth));
	}
	
	public static int getDepth(int n, int arity) {
		
		int sum = 0;
		int depth = 0;
		while (sum <= n) {
			sum += Math.pow(arity, depth);
			depth++;
		}
		return depth - 1;
		//return (int) (Math.log(n)/Math.log(arity));
	}
	
	public static int getArity(int n, int depth) {
		int arity;
		
		arity = 2;
		int max_nodes;
		while (true) {
			max_nodes = (int)Math.pow(arity, depth-1);
			System.out.println("MAX NODES IN ARITY " + arity + " = " + max_nodes);
			if (max_nodes < n) {
				arity++;
			} else {
				return arity;
			}
		}
	}
	 
	public static void createNodeProcess(String parent, Collection<String> childslist) {
		String childs = new String();
		for (String child : childslist) {
			childs += child + " ";
		}
		ProcessBuilder pb;
		if (parent != null) {
			pb = new ProcessBuilder("java node", "-id", "ID", "-pid", parent, "-nch", childs);
		} else {
			//Master-Node
			pb = new ProcessBuilder("java node", "-ch", childs);
		}
		try {
			pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int readNumber(String s) {
		int result;
		try {
			result = Integer.parseInt(s);
		} catch (Exception e) {
			System.out.println("ERROR: Argument not a number");
			result = -1;
		}
		return result;
	}
}
