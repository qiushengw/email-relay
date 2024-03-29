package email.relay.smtp.command;

import java.io.IOException;

import email.relay.smtp.server.BaseCommand;
import email.relay.smtp.server.Session;

/**
 * 
 * @author Michele Zuccala < zuccala.m@gmail.com >
 */
public class ExpandCommand extends BaseCommand 
{
	/** */
	public ExpandCommand() 
	{
		super("EXPN", "The expn command.");
	}

	/** */
	@Override
	public void execute(String commandString, Session sess) throws IOException 
	{
		sess.sendResponse("502 EXPN command is disabled");
	}
}
