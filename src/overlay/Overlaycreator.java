package overlay;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

public class Overlaycreator {
	
	private static LinkedList<Process> processList = new LinkedList<Process>();

	public static void main(String[] args) {
		 
		if (args.length != 4) {
			System.out.println("Number of arguments is wrong");
			return;
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
		PseudoNode root;
		if (mode.equals("arity")) {
			//System.out.println("Creating NodeTree of " + n + " nodes with arity " + value);
			root = createTreeWithArity(n, value);
			
			//System.out.println("Depth=" + getDepth(n,value));
		} else {
			//System.out.println("Creating NodeTree of " + n + " nodes with depth " + value);
			//System.out.println("Arity=" + getArity(n, value));
			root = createTreeWithDepth(n, value);
			
		}
		printTree(root);
		createOverlay(root);
		
		//Destroy node processes at the end
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Destroying Node Processes");
			for (Process p : processList) {
				System.out.println(p.toString());
				p.destroy();
			}
		}
	}
	 
	public static PseudoNode createTreeWithArity(int n, int arity) {
		return createTree(n,arity,getDepth(n,arity));
	}
	
	public static PseudoNode createTreeWithDepth(int n, int depth) {
		return createTree(n,getArity(n,depth), depth);
	}
	
	public static boolean addChild(PseudoNode child, PseudoNode root, int arity, int depth) {
		if (depth == 0) {
			return false;
		}
		if (root.getNumberOfChilds() < arity) {
			root.addChild(child);
			child.setParent(root);
			return true;
		} else {
			for (PseudoNode tmp : root.getChilds()) {
				if (addChild(child, tmp, arity, depth-1)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static PseudoNode createTree(int n, int arity, int depth) {
		System.out.println("Tree will have " + n + " Nodes, an arity of " + arity + " and a depth of " + depth);
		LinkedList<PseudoNode> list = new LinkedList<PseudoNode>();
		PseudoNode pn, root = null;
		int currentDepth = 0;
		int count = 0;
		while (count <= n) {
			pn = new PseudoNode(count);
			count++;
			list.add(pn);
			if (root == null) {
				root = pn;
				currentDepth++;
				continue;
			}
			addChild(pn, root, arity, depth-1);
		}
//			if (Math.pow(arity,currentDepth) > count) {
//				currentDepth++;
//			}
//			
//			int sum=0;
//			for (int i = 0; i < currentDepth-1; i++) {
//				sum += Math.pow(arity, i);
//			}
//			int minChildsNode = 0;
//			for (int i = 0; i < sum; i++) {
//				if (((PseudoNode) list.get(i)).getNumberOfChilds() < ((PseudoNode) list.get(minChildsNode)).getNumberOfChilds()) {
//					minChildsNode = i;
//				}
//			}
//			PseudoNode parent = ((PseudoNode) list.get(minChildsNode));
//			parent.addChild(pn);
//			pn.setParent(parent);
//	
//		}
//		
		return root;
		
	}
	
	public static int getDepth(int n, int arity) {
		
		int sum = 0;
		int depth = 0;
		while (sum < n) {
			
			sum += Math.pow(arity, depth);
			depth++;
//			System.out.println("SUM="+sum);
//			System.out.println("DEPTH=" + depth);
		}
		return depth;
		//return (int) (Math.log(n)/Math.log(arity));
	}
	
	public static int getArity(int n, int depth) {
		int arity = 1;
		while (true) {
			if (getDepth(n, arity) == depth) {
				return arity;
			}
			arity++;
		}
//		int arity;
//		
//		arity = 2;
//		int max_nodes;
//		while (true) {
//			max_nodes = (int)Math.pow(arity, depth-1);
//			System.out.println("MAX NODES IN ARITY " + arity + " = " + max_nodes);
//			if (max_nodes < n) {
//				arity++;
//			} else {
//				return arity;
//			}
//		}
	}
	
	public static void printTree(PseudoNode pn) {
		System.out.println(pn);
		Collection<PseudoNode> childs = pn.getChilds();
		for (PseudoNode child : childs) {
			printTree(child);
		}
	}
	
	public static void createOverlay(PseudoNode pn) {
		createNodeProcess(pn);
		Collection<PseudoNode> childs = pn.getChilds();
		for (PseudoNode child : childs) {
			createNodeProcess(child);
			createOverlay(child);
		}
	}
	 
	public static void createNodeProcess(PseudoNode pn) {
		ProcessBuilder pb = new ProcessBuilder();
		//Alternative to ProcessBuilder: Runtime.getRuntime().exec("cscript java -classpath ...");
		System.out.println("Create Node Process:");
		System.out.println(pn);
		
		//Set Path of Java Runtime
		File dir = new File(System.getenv("JAVA_HOME"));
		//System.out.println("PROP = " + System.getenv("JAVA_HOME"));
		pb.directory(dir);
		
		//Filename of executable jar file
		String nodeprocess = "StartNode.jar";
		
		//Set Path of Jar file
		File jarfile = new File(nodeprocess);
		//System.out.println("NODE: " + f.getAbsolutePath());
		if (pn.getParent() != null) {
			//Middle-Node
			System.out.println("NON-MASTER");
			pb.command("java", "-jar", jarfile.getAbsolutePath());//, "-id", Integer.toString(pn.getId()), "-pid", Integer.toString(pn.getParent().getId()), "-nch", Integer.toString(pn.getNumberOfChilds()));

		} else {
			//Master-Node
			System.out.println("MASTER");
			pb.command("java", "-jar", jarfile.getAbsolutePath());//, "-id", Integer.toString(pn.getId()), "-nch", Integer.toString(pn.getNumberOfChilds()));
		}


		//System.out.println(pb.directory());
		//Run process
		try {
			processList.add(pb.start());
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

