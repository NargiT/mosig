package utils;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
	
	String text;
	java.util.Date date;
	String from;
	
	public Message(String text, String from) {
		super();
		this.text = text;
		this.from = from;
		date = new java.util.Date();
	}
	
	//Needed because otherwise there is instantiation error for xml-object to be added to history
	public Message() {
        from = "blub";
        text = "blub";
        date = new Date();
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
	

}
