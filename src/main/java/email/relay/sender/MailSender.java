package email.relay.sender;

import email.relay.wiser.WiserMessage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface MailSender {

     void send(WiserMessage relayMessage);

}
