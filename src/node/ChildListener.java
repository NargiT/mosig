package node;

import node.News;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.Semaphore;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.jms.JMSException;

public class ChildListener implements MessageListener {

	private double memoryUsed;
	private Semaphore semaphore;

	public ChildListener() {
		super();
		memoryUsed = 0.0;
		semaphore = new Semaphore(1);
	}

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			double latestNews = Double.parseDouble(((TextMessage) message).getText());
			try {
				semaphore.acquire();
				// Has to be updated
				news = latestNews.get
				semaphore.release();
			} catch (InterruptedException e) {
				System.err.println("Failed to get acquire the lock: "
						+ e.toString());
			}
		}
	}

	public News getNews() throws InterruptedException {
		News toReturn = new News(from, memoryUsed);
		semaphore.acquire();
		
		semaphore.release();
		return toReturn;
	}

}
