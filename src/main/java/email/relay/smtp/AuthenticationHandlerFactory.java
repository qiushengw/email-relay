package email.relay.smtp;

import java.util.List;

public interface AuthenticationHandlerFactory
{
	public List<String> getAuthenticationMechanisms();
	public AuthenticationHandler create();

}
