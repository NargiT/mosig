package chat.utils;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

    private String text;
    private Date date;
    private String from;

    public Message(String from, String text, Date date) {
        super();
        this.from = from;
        this.text = text;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }
}
