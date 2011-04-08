package node;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import sensor.MemorySensor;

public class Node {

	private int id;
	private NodeStatus status;
	private int numberOfChild;

	private String publisherTopicName;
	private String subscriberTopicName;

	private InitialContext jndiContext;
	private ConnectionFactory factory;
	private Topic publishTopic;
	private Topic subscribedTopic;
	private Connection connection;
	private Session session;

	private MessageConsumer receiver;
	private MessageProducer sender;

	private MemoryListener listen;
	private MemorySensor memory;

	/**
	 * Create a virtual node representing a server
	 * 
	 * @param id
	 *            unique identifier of the node
	 * @param publisherTopicName
	 *            topic published if it has a parent null otherwise
	 * @param subscriberTopicName
	 *            topic subscribed if it has at least one child
	 * @param numberOfChild
	 *            number of child publishing in {@code subscriberTopicName}
	 */
	public Node(int id, String publisherTopicName, String subscriberTopicName,
			int numberOfChild) {
		this.id = id;
		this.publisherTopicName = publisherTopicName;
		this.subscriberTopicName = subscriberTopicName;
		this.numberOfChild = numberOfChild;
		this.jndiContext = null;
		this.factory = null;
		this.publishTopic = null;
		this.subscribedTopic = null;
		this.connection = null;
		this.session = null;
		this.receiver = null;
		this.sender = null;
		this.listen = new MemoryListener();
		this.memory = new MemorySensor();
		setStatus();
		setUp();
	}

	public void start() throws IOException {
		createSubscribtion();
		createPublication();
		try {
			connection.start();
			if (status != NodeStatus.LEAF)
				receiver.setMessageListener(listen);

			while (true) {
				Thread.sleep(5000);
				/**
				 * Retrieve some data from JMX
				 */
				long l = memory.getMemory();
				l += listen.getLatestNews();
				if (status != NodeStatus.MASTER) {
					News n = new News(id, l);
					ObjectMessage msg = session.createObjectMessage();
					msg.setObject(n);
					sender.send(msg);
				} else {
					System.out.println(status + ": the average memory used is:"
							+ l / 5);
				}
			}
		} catch (JMSException e) {
			System.out.println(status + " " + e.toString());
		} catch (InterruptedException e) {
			System.out.println(status + "Couldn't sleep " + e.toString());
		} finally {
			// close the context
			if (jndiContext != null) {
				try {
					jndiContext.close();
				} catch (NamingException e) {
					System.exit(1);
				}
			}

			// close the connection
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					System.exit(1);
				}
			}
		}
	}

	/**
	 * 
	 */
	private void createSubscribtion() {
		if (status == NodeStatus.LEAF)
			return;
		System.out.println(status + " " + id + " : start subscribtion");
		try {
			receiver = session.createConsumer((Destination) subscribedTopic);
		} catch (JMSException e) {
			System.out.println(status + " Sesssion could not create a consumer"
					+ e.toString());
			System.exit(1);
		}
	}

	/**
	 * 
	 */
	private void createPublication() {
		if (status == NodeStatus.MASTER)
			return;
		System.out.println(status + " " + id + " : start publishing.");
		try {
			sender = session.createProducer((Destination) publishTopic);
		} catch (JMSException e) {
			System.out.println(status + " Sesssion could not create a producer"
					+ e.toString());
			System.exit(1);
		}
	}

	/**
	 * Create connection. Create session from connection; false means session is
	 * not transacted.
	 */
	private void setUp() {
		try {
			jndiContext = new InitialContext();
		} catch (NamingException e) {
			System.out.println("Could not create JNDI API " + "context: "
					+ e.toString());
			System.exit(1);
		}

		/*
		 * Look up connection factory.
		 */
		try {
			factory = (ConnectionFactory) jndiContext
					.lookup("ConnectionFactory");
		} catch (NamingException e) {
			System.out.println("JNDI API lookup failed: " + e.toString());
			System.exit(1);
		}

		/*
		 * Look up the topic
		 */
		try {
			switch (status) {
			case MASTER:
				subscribedTopic = (Topic) jndiContext
						.lookup(subscriberTopicName);
				break;
			case MIDDLE:
				subscribedTopic = (Topic) jndiContext
						.lookup(subscriberTopicName);
				publishTopic = (Topic) jndiContext.lookup(publisherTopicName);
				break;
			case LEAF:
				publishTopic = (Topic) jndiContext.lookup(publisherTopicName);
				break;
			}
		} catch (NamingException e) {
			System.out.println("JNDI API lookup failed for topic: "
					+ e.toString());
			System.exit(1);
		}

		try {
			connection = factory.createConnection();
		} catch (JMSException e) {
			System.out.println(status
					+ " factory could not create a connection " + e.toString());
		}
		try {
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			System.out.println(status
					+ " connection could not create a session " + e.toString());
		}

	}

	private void setStatus() {
		if (publisherTopicName.isEmpty())
			status = NodeStatus.MASTER;
		else if (!publisherTopicName.isEmpty() && numberOfChild == 0)
			status = NodeStatus.LEAF;
		else
			status = NodeStatus.MIDDLE;
	}
}
