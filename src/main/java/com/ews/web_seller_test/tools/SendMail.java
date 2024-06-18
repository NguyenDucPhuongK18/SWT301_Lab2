package com.ews.web_seller_test.tools;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {

    public static boolean sendMail(String to, String subject, String text) {
        final String from = "trongwilson2k3@gmail.com";
        final String password = "zchhntmkzvxduuls";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

    public static boolean sendContactMail(String email, String subject, String mess, String name) {
        final String adminEmail = "trongwilson2k3@gmail.com";
        String text = "My Name: " + name + "\n" +
                "My Email: " + email + "\n" +
                "Message: " + mess;
        return sendMail(adminEmail, subject, text);
    }

}

