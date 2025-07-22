package com.mycompany.librarymanagement.util;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailUtility {

    public static void sendMembershipEmail(String toEmail, String membershipNumber, String password, String role) {
        final String fromEmail = "your_email@gmail.com"; // Replace with your email
        final String emailPassword = "your_app_password"; // Replace with app-specific password

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, emailPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail, "Library Management System"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Your Library Membership Details");

            String content = "<h3>Welcome to Library Management System</h3>"
                    + "<p><strong>Role:</strong> " + role + "</p>"
                    + "<p><strong>Membership Number:</strong> " + membershipNumber + "</p>"
                    + "<p><strong>Password:</strong> " + password + "</p>"
                    + "<p>You can now log in using these credentials.</p>";

            message.setContent(content, "text/html; charset=utf-8");
            Transport.send(message);

            System.out.println("Email sent successfully to " + toEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
