
package utils;

import java.io.File;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailUtils {

    public static void sendEmail(
            String recipientEmail,
            String subject,
            String emailBody,
            String resumePath) {

        final String senderEmail =
                "krishnareddyt25@gmail.com";

        final String appPassword =
                "gknxbtzucfdzqacy";

        // SMTP Server Properties
        Properties prop = new Properties();

        prop.put(
                "mail.smtp.auth",
                "true"
        );

        prop.put(
                "mail.smtp.host",
                "smtp.gmail.com"
        );

        prop.put(
                "mail.smtp.starttls.enable",
                "true"
        );

        prop.put(
                "mail.smtp.port",
                "587"
        );

        // Create session with Authentication
        Session session =
                Session.getInstance(
                        prop,
                        new Authenticator() {

                            protected PasswordAuthentication
                            getPasswordAuthentication() {

                                return new PasswordAuthentication(
                                        senderEmail,
                                        appPassword
                                );
                            }
                        }
                );

        try {

            // Create Email message
            Message message =
                    new MimeMessage(session);

            message.setFrom(
                    new InternetAddress(senderEmail)
            );

            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail)
            );

            message.setSubject(subject);

            // Email Body
            MimeBodyPart textPart =
                    new MimeBodyPart();

            textPart.setText(emailBody);

            // Resume Attachment
            MimeBodyPart attachmentPart =
                    new MimeBodyPart();

            System.out.println(
                    "Attachment path: "
                    + resumePath
            );

            attachmentPart.attachFile(
                    new File(resumePath)
            );

            // Combine body and attachment
            MimeMultipart multipart =
                    new MimeMultipart();

            multipart.addBodyPart(textPart);

            multipart.addBodyPart(
                    attachmentPart
            );

            message.setContent(multipart);

            // Send Email
            Transport.send(message);

            System.out.println(
                    "Email Sent Successfully to: "
                    + recipientEmail
            );

        } catch (Exception e) {

            System.out.println(
                    "Failed to send email to: "
                    + recipientEmail
            );

            e.printStackTrace();
        }
    }

}