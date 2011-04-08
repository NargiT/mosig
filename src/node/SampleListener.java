package node;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.jms.JMSException;

public class SampleListener implements MessageListener {

	public SampleListener() {
		super();
	}

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage text = (TextMessage) message;
			try {				
				System.out.println("Received: " + text.getText());
			} catch (JMSException e) {
				System.err.println("Failed to get message text: " + e.toString());
			}
		}
	}

}
