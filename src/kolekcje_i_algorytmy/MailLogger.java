package kolekcje_i_algorytmy;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


public class MailLogger extends UnicastRemoteObject implements Logger{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7368464782243013112L;
	String to;
	String from;
	String pass;
	String subject="Java mail test";
	String host;
    String msgText;

    public MailLogger(String to, String from, String host, String pass) throws RemoteException{
    	this.to=to;
    	this.from=from;
    	this.host=host;
    	this.pass=pass;
    }
	public void log(String status, Student student){
		subject="Crawler Notification ("+status+" person)";
    	msgText = status+" : "+student;
		
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.setProperty("mail.smtp.user", from);
		props.setProperty("mail.smtp.password", pass);
		props.setProperty("mail.smtp.auth", "true"); 
		Session session = Session.getInstance(props, null);
		
		try {

		    MimeMessage msg = new MimeMessage(session);
		    msg.setFrom(new InternetAddress(from));
		    InternetAddress[] address = {new InternetAddress(to)};
		    msg.setRecipients(Message.RecipientType.TO, address);
		    msg.setSubject(subject);
		    msg.setSentDate(new Date());
		    msg.setText(msgText);
		    Transport.send(msg,from,pass);
		} catch (MessagingException mex){
			mex.printStackTrace();
		}
	}
}
