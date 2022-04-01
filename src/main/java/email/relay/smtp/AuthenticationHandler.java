package email.relay.smtp;

public interface AuthenticationHandler
{

	public String auth(String clientInput) throws RejectException;
	public Object getIdentity();
}
