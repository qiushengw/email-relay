/*
 * $Id$
 * $Source: /cvsroot/Similarity4/src/java/com/similarity/mbean/BindStatisticsManagerMBean.java,v $
 */
package email.relay.smtp;

@SuppressWarnings("serial")
public class RejectException extends RuntimeException
{
	int code;
	public RejectException()
	{
		this("Transaction failed");
	}

	public RejectException(String message)
	{
		this(554, message);
	}
	public RejectException(int code, String message)
	{
		super(message);

		this.code = code;
	}

	public int getCode()
	{
		return this.code;
	}
	public String getErrorResponse()
	{
		return this.code + " " + this.getMessage();
	}
}
