package utils;

import java.io.Serializable;
import java.util.Date;

/**
 * Class represents the message send throw the network
 *
 */
public class Message implements Serializable {

    /**
     * Represents the source client of the message
     */
    String from;

    /**
     * Represents the data send by the source client
     */
    String text;

    /**
     * Represents the datetime of the message
     */
    Date date;

    /**
     * Public constructor, the field date is instanciate with the current time
     * @param text data send by the source client
     * @param from the source client
     */
    public Message(String text, String from) {
        super();
        this.text = text;
        this.from = from;
        date = new java.util.Date();
    }

    /**
     * Default constructor needed for the seralization of the {@link XMLTools}
     * Initialize the message with the {@code from} by "Darth Vader" and
     * {@code text} by "I'm your father". The {@code date} is initialize by the
     * current time
     */
    public Message() {
        from = "Darth Vader";
        text = "I'm your father";
        date = new Date();
    }

    /**
     * Return the text send throw the network
     * @return text
     */
    public String getText() {
        return text;
    }

    /**
     * Set the message text
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Return the date when the message was created or sent
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the message date
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Return the name of the sender
     * @return nickname
     */
    public String getFrom() {
        return from;
    }

    /**
     * Set the source of message
     * @param from new nickname of the message
     */
    public void setFrom(String from) {
        this.from = from;
    }
}
