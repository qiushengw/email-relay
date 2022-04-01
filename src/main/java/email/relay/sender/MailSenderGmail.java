package email.relay.sender;

import email.relay.wiser.WiserMessage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailSenderGmail implements MailSender {

    public void send(WiserMessage relayMessage)
    {
        Properties systemProperties = System.getProperties();

        String username = (String) systemProperties.get("mail.smtp.user");
        String password = (String) systemProperties.get("mail.smtp.pass");

        String smtpHost = (String) systemProperties.get("mail.smtp.host");
        String smtpPort = (String) systemProperties.get("mail.smtp.socketFactory.port");
        String starttlsEnable = (String) systemProperties.get("mail.smtp.starttls.enable");
        String starttlsRequired = (String) systemProperties.get("mail.smtp.starttls.required");
        String protocols = (String) systemProperties.get("mail.smtp.ssl.protocols");
        String auth = (String) systemProperties.get("mail.smtp.auth");

        System.out.println("==smtpHost==" + smtpHost);
        System.out.println("==smtpPort==" + smtpPort);
        System.out.println("==starttlsEnable==" + starttlsEnable);
        System.out.println("==starttlsRequired==" + starttlsRequired);
        System.out.println("==protocols==" + protocols);
        System.out.println("==auth==" + auth);


        Properties prop = new Properties();
        prop.put("mail.smtp.host", smtpHost);
        prop.put("mail.smtp.socketFactory.port", smtpPort);
        prop.put("mail.smtp.starttls.enable", starttlsEnable);
        prop.put("mail.smtp.starttls.required", starttlsRequired);
        prop.put("mail.smtp.ssl.protocols", protocols);
        prop.put("mail.smtp.auth", auth);

        String recipient = relayMessage.getEnvelopeReceiver();
        String sender = relayMessage.getEnvelopeSender();
        String subject = "";
        String content = "";
        String orgContent = new String(relayMessage.getData());

        System.out.println("=====ORG Content Start=======");
        System.out.println(orgContent);
        System.out.println("======ORG Content END======");

        System.out.println("sender:" + sender);
        System.out.println("recipient:" + recipient);

        Pattern subjectPattern = Pattern.compile("\r\nSubject:(.*)\r\n");
        Matcher subjectMatcher = subjectPattern.matcher(orgContent);

        if(subjectMatcher.find()){
            subject = subjectMatcher.group(1);
        }
        System.out.println("subject:" + subject);

        Pattern contentPattern = Pattern.compile("\r\n\r\n(.*)");

        int contentIndex = orgContent.indexOf("\r\n\r\n");

        if(contentIndex>-1){
            content = orgContent.substring(contentIndex+1);
        }

        System.out.println("content:" + content);

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(sender));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipient)
            );
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
