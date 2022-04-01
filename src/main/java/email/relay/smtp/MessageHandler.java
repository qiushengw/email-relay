/*
 * $Id$
 * $URL$
 */
package email.relay.smtp;

import java.io.IOException;
import java.io.InputStream;

/**
 * The interface that defines the conversational exchange of a single message on
 * an SMTP connection. Using the term "mail transaction", as defined by RFC 
 * 5321, implementing classes of this interface track a single mail transaction. 
 * The methods will be called in the following order:
 *
 * <ol>
 * <li><code>from()</code></li>
 * <li><code>recipient()</code> (possibly more than once)</li>
 * <li><code>data()</code></li>
 * <li><code>done()</code></li>
 * </ol>
 *
 * If multiple messages are delivered on a single connection (ie, using the RSET command)
 * then multiple message handlers will be instantiated.  Each handler services one
 * and only one message.
 *
 * @author Jeff Schnitzer
 */
public interface MessageHandler
{
	public void from(String from) throws RejectException;
	public void recipient(String recipient) throws RejectException;
	public void data(InputStream data) throws RejectException, TooMuchDataException, IOException;
	public void done();
}
