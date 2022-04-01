/*
 * $Id$
 * $URL$
 */

package email.relay.wiser;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;

import email.relay.sender.MailSenderGmail;
import email.relay.smtp.helper.SimpleMessageListener;
import email.relay.smtp.helper.SimpleMessageListenerAdapter;
import email.relay.smtp.server.SMTPServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import email.relay.sender.MailSender;
import email.relay.smtp.TooMuchDataException;

public class Wiser implements SimpleMessageListener
{
	private final static Logger log = LoggerFactory.getLogger(Wiser.class);
	SMTPServer server;
	protected List<WiserMessage> messages = Collections.synchronizedList(new ArrayList<WiserMessage>());
	public Wiser()
	{
		this.server = new SMTPServer(new SimpleMessageListenerAdapter(this));
	}
	public Wiser(int port)
	{
		this();
		this.setPort(port);
	}

	public void setPort(int port)
	{
		this.server.setPort(port);
	}

	public void setHostname(String hostname)
	{
		this.server.setHostName(hostname);
	}
	public void start()
	{
		this.server.start();
	}
	public void stop()
	{
		this.server.stop();
	}
	public boolean accept(String from, String recipient)
	{
		//if (log.isDebugEnabled())
			log.debug("Accepting mail from " + from + " to " + recipient);
		System.out.println("Accepting mail from " + from + " to " + recipient);
		return true;
	}
	MailSender mailSender = new MailSenderGmail();
	/** Cache the messages in memory */
	public void deliver(String from, String recipient, InputStream data) throws TooMuchDataException, IOException
	{
	//	if (log.isDebugEnabled())
			log.debug("Delivering mail from " + from + " to " + recipient);
		System.out.println("HERE: Delivering mail from " + from + " to " + recipient);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		data = new BufferedInputStream(data);

		// read the data from the stream
		int current;
		while ((current = data.read()) >= 0)
		{
			out.write(current);
		}

		byte[] bytes = out.toByteArray();

		if (log.isDebugEnabled())
			log.debug("Creating message from data with " + bytes.length + " bytes");
		System.out.println("HERE: Creating message from data with " + bytes.length + " bytes" + new String(bytes));
		// create a new WiserMessage.
		//this.messages.add(new WiserMessage(this, from, recipient, bytes));

		mailSender.send(new WiserMessage(this, from, recipient, bytes));
	}

	/**
	 * Creates the JavaMail Session object for use in WiserMessage
	 */
	protected Session getSession()
	{
		return Session.getDefaultInstance(new Properties());
	}

	public List<WiserMessage> getMessages()
	{
		return this.messages;
	}
	public SMTPServer getServer()
	{
		return this.server;
	}
	public void dumpMessages(PrintStream out) throws MessagingException
	{
		out.println("----- Start printing messages -----");

		for (WiserMessage wmsg: this.getMessages())
			wmsg.dumpMessage(out);

		out.println("----- End printing messages -----");
	}

	public static void main(String[] args) throws Exception
	{
		Properties props = System.getProperties();
		System.out.println("Properties props:" + props);
		log.debug("Properties props" + props);


		log.debug("Accepting mail from" + "tesitng start");
		System.out.println("Accepting mail from tesitng start");


		Wiser wiser = new Wiser();
		wiser.start();
	}
}
