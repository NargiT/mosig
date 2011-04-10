package overlay;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * 
 * The class overlaycreator creates an overlay of nodes and provides a main
 * method that is used to interact with the user on the console. Information
 * relevant to the tree of nodes (such as the number of nodes, the height of the
 * tree and the arity of the tree) is parsed from the user and a virtual tree is
 * built. The information saved in the nodes of the virtual tree is then used to
 * start single node processes with the correct arguments.
 * 
 * @author Laurent
 */
public class OverlayCreation {

	/**
	 * List of processes to handle the running node-processes
	 */
	private static LinkedList<Process> processList = new LinkedList<Process>();
	/**
	 * Path to the Java Home Directory
	 */
	private static File javaDir = new File(System.getenv("JAVA_HOME"));
	/**
	 * Path to the executable Jar file for the node process
	 */
	private static File jarFile = new File("StartNode.jar");

	/**
	 * Main method parses the arguments (number of nodes and depth or arity)
	 * from the user and starts the procedures to build a tree and then starts
	 * the node processes.
	 * 
	 * @param args
	 *            Arguments passed to the command
	 * 
	 */
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
		VirtualNode root;
		if (mode.equals("arity")) {
			root = createTreeWithArity(n, value);
		} else {
			root = createTreeWithDepth(n, value);
		}
		printTree(root);
		createOverlay(root);

		// Destroy node processes at the end
		try {
			System.out.println("\nPress ENTER to end the running processes");
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Destroying Node Processes:");
			for (Process p : processList) {
				System.out.println(p.toString());
				p.destroy();
			}
		}
	}

	/**
	 * Calls createTree with the additional argument depth. The depth is
	 * calculated using the function getDepth().
	 * 
	 * @param n
	 *            number of nodes of the tree
	 * @param arity
	 *            arity of the tree
	 */
	public static VirtualNode createTreeWithArity(int n, int arity) {
		return createTree(n, arity, getDepth(n, arity));
	}

	/**
	 * Calls createTree with the additional argument arity. The arity is
	 * calculated using the function getArity().
	 * 
	 * @param n
	 *            number of nodes of the tree
	 * @param depth
	 *            depth of the tree
	 */
	public static VirtualNode createTreeWithDepth(int n, int depth) {
		return createTree(n, getArity(n, depth), depth);
	}

	/**
	 * Adds a child to a virtual tree starting from the node given as root.
	 * Information about depth and arity is used to create the child at the
	 * right position.
	 * 
	 * @param child
	 *            child to be added to the tree
	 * @param root
	 *            root of the tree
	 * @param arity
	 *            arity of the tree
	 * @param depth
	 *            depth of the tree
	 * 
	 * @return true if the function succeeded in adding a child, false otherwise
	 */
	public static boolean addChild(VirtualNode child, VirtualNode root,
			int arity, int depth) {
		if (depth == 0) {
			return false;
		}
		if (root.getNumberOfChilds() < arity) {
			root.addChild(child);
			child.setParent(root);
			return true;
		} else {
			for (VirtualNode tmp : root.getChilds()) {
				if (addChild(child, tmp, arity, depth - 1)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Builds the virtual tree of nodes by creating a root node and subsequently
	 * calling the method addChild().
	 * 
	 * @param n
	 *            number of nodes in the tree
	 * @param arity
	 *            arity of the tree
	 * @param depth
	 *            depth of the tree
	 * @return root of the tree.
	 */
	public static VirtualNode createTree(int n, int arity, int depth) {
		System.out.println("Tree will have " + n + " Nodes, an arity of "
				+ arity + " and a depth of " + depth);
		VirtualNode vn, root = null;
		int count = 0;
		while (count <= n) {
			vn = new VirtualNode(count);
			count++;
			if (root == null) {
				root = vn;
			} else {
				addChild(vn, root, arity, depth - 1);
			}
		}
		return root;
	}

	/**
	 * Algorithm to calculate the depth of the tree from given information such
	 * as number of nodes and arity of the tree.
	 * 
	 * @param n
	 *            number of nodes in the tree
	 * @param arity
	 *            arity of the tree
	 * 
	 * @return Depth of the tree
	 */
	public static int getDepth(int n, int arity) {

		int sum = 0;
		int depth = 0;
		while (sum < n) {

			sum += Math.pow(arity, depth);
			depth++;
		}
		return depth;
	}

	/**
	 * Algorithm to calculate the arity of the tree from given information such
	 * as number of nodes and depth of the tree. Makes use of the method
	 * getDepth() to calculate the right arity for the given depth.
	 * 
	 * @param n
	 *            number of nodes in the tree
	 * @param depth
	 *            depth of the tree
	 * 
	 * @return Arity of the tree
	 */
	public static int getArity(int n, int depth) {
		int arity = 1;
		while (true) {
			if (getDepth(n, arity) == depth) {
				return arity;
			}
			arity++;
		}
		// or you can also write
		// while (getDepth(n, arity) != depth) {
		// arity++;
		// }
		// return arity;
	}

	/**
	 * Method prints out all information on the single nodes in the tree.
	 * 
	 * @param root
	 *            Root of the tree.
	 * 
	 */
	public static void printTree(VirtualNode root) {
		System.out.println(root);
		Collection<VirtualNode> childs = root.getChilds();
		for (VirtualNode child : childs) {
			printTree(child);
		}
	}
	
	/**
	 * Method returns the number of subnodes of a root
	 * 
	 * @param root
	 *            Root of the tree.
	 * 
	 * @return Number of Subnodes
	 */
	public static int getNumberOfSubNodes(VirtualNode root) {
		
		int n = root.getNumberOfChilds();
		Collection<VirtualNode> childs = root.getChilds();
		for (VirtualNode child : childs) {
			n += getNumberOfSubNodes(child);
		}
		
		return n;
	}

	/**
	 * Starts node processes of the root node and then traverses the tree to
	 * start the processes of all the subnodes.
	 * 
	 * @param root
	 *            Root of the tree.
	 * 
	 */
	public static void createOverlay(VirtualNode root) {
		createNodeProcess(root);
		Collection<VirtualNode> childs = root.getChilds();
		for (VirtualNode child : childs) {
			createOverlay(child);
		}
	}

	/**
	 * Starts node process of the node given as parameter. Master and Child
	 * nodes are handed differently. Processbuilder is used to handle the
	 * process and to add the node process to a global list of processes.
	 * 
	 * @param vn
	 *            Virtual Node to get the required information from in order to
	 *            start the node-process
	 * 
	 */
	public static void createNodeProcess(VirtualNode vn) {
		ProcessBuilder pb = new ProcessBuilder();

		System.out.println("Create Node Process:");
		System.out.println(vn);

		// Set Path of processbuilder to the directory of the JRE
		pb.directory(javaDir);

		/*
		 * Set the command to start and its Parameters Depending if the node is
		 * a master node or a leaf node.
		 */
		// Leaf
		if (vn.getParent() != null) {
			System.out.println("LEAF");
			pb.command("java", "-jar", jarFile.getAbsolutePath(), "-id",
					Integer.toString(vn.getId()), "-pid",
					Integer.toString(vn.getParent().getId()), "-nch",
					Integer.toString(vn.getNumberOfChilds()));
			// Master
		} else {
			System.out.println("MASTER");
			pb.command("java", "-jar", jarFile.getAbsolutePath(), "-id",
					Integer.toString(vn.getId()), "-nch",
					Integer.toString(vn.getNumberOfChilds()), "-n", Integer.toString(getNumberOfSubNodes(vn)));
		}

		// Run the process
		try {
			Process p = pb.start();
			processList.add(p);
			System.out.println("Java process " + p.toString() + " started");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Reads a String from the input of a user and tries to convert that string
	 * into a number.
	 * 
	 * @param s
	 *            String that is read and to be interpreted as a number.
	 * 
	 * @return The number read by the user or (-1) in case of an error when
	 *         converting the string.
	 */
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
