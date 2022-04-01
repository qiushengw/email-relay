package email.relay.smtp.command;

import java.io.IOException;

import email.relay.smtp.server.BaseCommand;
import email.relay.smtp.server.Session;

/**
 * @author Ian McFarland &lt;ian@neo.com&gt;
 * @author Jon Stevens
 * @author Jeff Schnitzer
 */
public class NoopCommand extends BaseCommand
{
	/** */
	public NoopCommand()
	{
		super("NOOP", "The noop command");
	}

	/** */
	@Override
	public void execute(String commandString, Session sess) throws IOException
	{
		sess.sendResponse("250 Ok");
	}
}