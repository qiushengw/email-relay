package email.relay.wiser;

import java.io.ByteArrayInputStream;
import java.io.PrintStream;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


public class WiserMessage
{
	byte[] messageData;
	Wiser wiser;
	String envelopeSender;
	String envelopeReceiver;

	WiserMessage(Wiser wiser, String envelopeSender, String envelopeReceiver, byte[] messageData)
	{
		this.wiser = wiser;
		this.envelopeSender = envelopeSender;
		this.envelopeReceiver = envelopeReceiver;
		this.messageData = messageData;
	}


	public MimeMessage getMimeMessage() throws MessagingException
	{
		return new MimeMessage(this.wiser.getSession(), new ByteArrayInputStream(this.messageData));
	}

	public byte[] getData()
	{
		return this.messageData;
	}

	public String getEnvelopeReceiver()
	{
		return this.envelopeReceiver;
	}

	public String getEnvelopeSender()
	{
		return this.envelopeSender;
	}

	public void dumpMessage(PrintStream out) throws MessagingException
	{
		out.println("===== Dumping message =====");

		out.println("Envelope sender: " + this.getEnvelopeSender());
		out.println("Envelope recipient: " + this.getEnvelopeReceiver());

		// It should all be convertible with ascii or utf8
		String content = new String(this.getData());
		out.println(content);

		out.println("===== End message dump =====");
	}


	@Override
	public String toString()
	{
		if (this.getData() == null)
			return "";

		return new String(this.getData());
	}
}
