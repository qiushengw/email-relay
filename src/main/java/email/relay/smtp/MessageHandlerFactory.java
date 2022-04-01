/*
 * $Id$
 * $URL$
 */
package email.relay.smtp;

public interface MessageHandlerFactory
{
	public MessageHandler create(MessageContext ctx);
}
