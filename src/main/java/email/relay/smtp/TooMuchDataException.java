/*
 * $Id$
 * $Source: /cvsroot/Similarity4/src/java/com/similarity/mbean/BindStatisticsManagerMBean.java,v $
 */
package email.relay.smtp;

import java.io.IOException;


@SuppressWarnings("serial")
public class TooMuchDataException extends IOException
{
	public TooMuchDataException()
	{
		super();
	}
	public TooMuchDataException(String message)
	{
		super(message);
	}
}
