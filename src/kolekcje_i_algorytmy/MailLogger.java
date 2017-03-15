package kolekcje_i_algorytmy;

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


public class MailLogger implements Logger {
	String to;
	String from;
	String subject="Java mail test";
	String host;
    String msgText;

    public MailLogger(String to, String from, String host){
    	this.to=to;
    	this.from=from;
    	this.host=host;
    }
	public void log(String status, Student student){
		subject="Crawler Notification ("+status+" person)";
    	msgText = status+" : "+student;
		
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		Session session = Session.getInstance(props, null);
		
		try {
		    MimeMessage msg = new MimeMessage(session);
		    msg.setFrom(new InternetAddress(from));
		    InternetAddress[] address = {new InternetAddress(to)};
		    msg.setRecipients(Message.RecipientType.TO, address);
		    msg.setSubject(subject);
		    msg.setSentDate(new Date());
		    msg.setText(msgText);
		    Transport.send(msg);
		} catch (MessagingException mex){
			mex.printStackTrace();
		}
	}
}
