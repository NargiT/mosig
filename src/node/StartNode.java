package node;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Logger;

import javax.jms.JMSException;

import org.exolab.jms.administration.AdminConnectionFactory;
import org.exolab.jms.administration.JmsAdminServerIfc;

public class StartNode {

	public static Logger logger = Logger.getLogger("trace");

	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		int id = -1, pid = -1, nch = 0, n = 0;
		String pTopicName = "", sTopicName = "";

		/**
		 * Parser
		 * 
		 * -id identifier of the node -pid identifier of the parent -nch number
		 * of children
		 **/
		if (args.length < 4) {
			System.err.println("Not enough arguments");
			System.exit(1);
		}

		// Check if -id option is right
		if (!args[0].equals("-id")) {
			System.err.println("The option " + args[0] + "  doesn't exist");
			System.exit(1);
		}
		id = Integer.parseInt(args[1]);

		// Parse the first argument
		if (args[2].equals("-pid"))
			pid = Integer.parseInt(args[3]);
		else if (args[2].equals("-nch"))
			nch = Integer.parseInt(args[3]);
		else {
			System.err.println("Usage: " + "-id node identifier\n"
					+ "[-pid] node parent identifier\n"
					+ "[-nch] node number of children"
					+ "[-n] number of total nodes");
		}

		// number of child
		if (args.length == 6 && args[4].equals("-nch"))
			nch = Integer.parseInt(args[5]);

		// if master node
		if (args.length == 6 && args[4].equals("-n")) {
			n = Integer.parseInt(args[5]);
		}
		/**
		 * Generate topic names
		 * 
		 * publisher : t_{parent_id} subscribe : t_{own_id}
		 **/
		if (pid != -1)
			pTopicName = "t_n" + String.valueOf(pid);

		if (nch > 0)
			sTopicName = "t_n" + String.valueOf(id);

		/**
		 * Connection with the administrator
		 */
		String url = "tcp://localhost:3035/";
		JmsAdminServerIfc admin;
		try {
			admin = AdminConnectionFactory.create(url);
			// /**
			// * Remove previous topics
			// */
			// Vector<?> destinations = admin.getAllDestinations();
			// //Iterator iterator = destinations.iterator();
			// for (Object destination : destinations) {
			// if (destination instanceof Queue) {
			// Queue queue = (Queue) destination;
			// admin.removeDestination(queue.getQueueName());
			// } else {
			// Topic topic = (Topic) destination;
			// admin.removeDestination(topic.getTopicName());
			// }
			// }

			/**
			 * Create the topics of the node
			 */
			if (pid != -1) {
				if (!admin.destinationExists(pTopicName))
					if (!admin.addDestination(pTopicName, false)) {
						System.out.println("Failed to create topic "
								+ pTopicName);
						System.exit(1);
					}
			}

			if (nch > 0) {
				if (!admin.destinationExists(sTopicName))
					if (!admin.addDestination(sTopicName, false)) {
						System.out.println("Failed to create topic "
								+ sTopicName);
						System.exit(1);
					}
			}
			/**
			 * Admin connection closed
			 */
			admin.close();
		} catch (MalformedURLException e1) {
			System.out.println("The url is mal formed " + e1.toString());
			System.exit(1);
		} catch (JMSException e1) {
			System.out.println("Admin fails to create topics" + e1.toString());
			System.exit(1);
		}

		/**
		 * Create the node
		 */
		Node node;
		if (id == 0) {
			node = new Node(id, pTopicName, sTopicName, nch, n);
		} else {
			node = new Node(id, pTopicName, sTopicName, nch);
		}
		node.start();
	}
}