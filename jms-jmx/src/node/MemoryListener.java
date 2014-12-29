package node;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.Semaphore;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Listener used to receive message asynchronously from the topic
 * 
 * @author Tigran Tchougourian
 * 
 */
public class MemoryListener implements MessageListener {

	/**
	 * Permit to save for each news its source and data sent
	 */
	private HashMap<Integer, Long> news;

	/**
	 * Care about data race
	 */
	private Semaphore semaphore;

	/**
	 * Initialize the attribute of the memory listener
	 */
	public MemoryListener() {
		super();
		news = new HashMap<Integer, Long>();
		semaphore = new Semaphore(1);
	}

	@Override
	/**
	 * For each message received, update the right value in the hashmap
	 */
	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			News latestNews;
			try {
				latestNews = (News) ((ObjectMessage) message).getObject();
				semaphore.acquire();
				news.put(latestNews.getFrom(), latestNews.getMemoryUsed());
				semaphore.release();
			} catch (JMSException e1) {
				e1.printStackTrace();
			} catch (InterruptedException e) {
				System.err.println("Failed to get acquire the lock: "
						+ e.toString());
			}
		}
	}

	/**
	 * 
	 * @return the latest news from the overlay
	 * @throws InterruptedException
	 *             if the current thread is interupt
	 */
	public long getLatestNews() throws InterruptedException {
		long toReturn = 0;
		semaphore.acquire();
		for (Entry<Integer, Long> entry : news.entrySet()) {
			toReturn += entry.getValue();
		}
		semaphore.release();
		return toReturn;
	}

}
