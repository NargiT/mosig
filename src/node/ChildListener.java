package node;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.Semaphore;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class ChildListener implements MessageListener {

	private HashMap<Integer, Long> news;
	private Semaphore semaphore;

	public ChildListener() {
		super();
		news = new HashMap<Integer, Long>();
		semaphore = new Semaphore(1);
	}

	@Override
	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			News latestNews = (News)((ObjectMessage) message);
			try {
				semaphore.acquire();
				news.put(latestNews.getFrom(), latestNews.getMemoryUsed());
				semaphore.release();
			} catch (InterruptedException e) {
				System.err.println("Failed to get acquire the lock: "
						+ e.toString());
			}
		}
	}

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
