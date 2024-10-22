package util;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class EmailUtil {
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final int SMTP_PORT = 465;
    private static final String SENDER_EMAIL = "clothify896@gmail.com";
    private static final String APP_PASSWORD = "ogih mqxi ofyb cqyl";

    public static void sendEmail(String to, String subject, String text) {
        Email email = EmailBuilder.startingBlank()
                .from(SENDER_EMAIL)
                .to(to)
                .withSubject(subject)
                .withPlainText(text)
                .buildEmail();

        Mailer mailer = MailerBuilder
                .withSMTPServer(SMTP_HOST, SMTP_PORT, SENDER_EMAIL, APP_PASSWORD)
                .withTransportStrategy(TransportStrategy.SMTPS)
                .buildMailer();

        mailer.sendMail(email);
    }
    public static void sendOTP(String to, int otp) {
        String subject = "Your OTP for Change Password";
        String text = "Your One-Time Password (OTP) is: " + otp + "\n\n" +
                "Please use this OTP to complete your verification process.\n" +
                "If you did not request this OTP, please ignore this email.";
        sendEmail(to, subject, text);
    }
}
