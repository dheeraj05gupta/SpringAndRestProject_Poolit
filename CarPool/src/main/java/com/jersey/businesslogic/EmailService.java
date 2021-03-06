package main.java.com.jersey.businesslogic;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailService {

	public EmailService() {

	}

	static final String SMTP_USERNAME = "AKIAIBOUBIYUZJXKQEEA"; 
	static final String SMTP_PASSWORD = "AiqhPKZM7f1mlBJ8BEHoYdU8gY90Cp+BZpQwuN0BkoK4"; 

	static final String HOST = "email-smtp.us-west-2.amazonaws.com";

	// Port we will connect to on the Amazon SES SMTP endpoint. We are choosing
	// port 25 because we will use
	// STARTTLS to encrypt the connection.
	static final int PORT = 25;

	public String sendEmail(String FROM, String TO, String BODY, String SUBJECT) throws MessagingException{

		/*
		 * static final String FROM = "anand.pateriya88@gmail.com"; // Replace
		 * with your "From" address. This address must be verified. static final
		 * String TO = "dheeraj05gupta@gmail.com"; // Replace with a "To"
		 * address. If you have not yet requested // production access, this
		 * address must be verified.
		 * 
		 * static final String BODY =
		 * "This email was sent through the Amazon SES SMTP interface by using Java."
		 * ; static final String SUBJECT =
		 * "Amazon SES test (SMTP interface accessed using Java)";
		 */

		// Supply your SMTP credentials below. Note that your SMTP credentials
		// are different from your AWS credentials.

		// Create a Properties object to contain connection configuration
		// information.
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", PORT);

		// Set properties indicating that we want to use STARTTLS to encrypt the
		// connection.
		// The SMTP session will begin on an unencrypted connection, and then
		// the client
		// will issue a STARTTLS command to upgrade to an encrypted connection.
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.required", "true");

		// Create a Session object to represent a mail session with the
		// specified properties.
		Session session = Session.getDefaultInstance(props);

		// Create a message with the specified information.
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(FROM));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
		msg.setSubject(SUBJECT);
		msg.setContent(BODY, "text/plain");

		// Create a transport.
		Transport transport = session.getTransport();

		// Send the message.
		try {
			System.out.println("Attempting to send an email through the Amazon SES SMTP interface...");

			// Connect to Amazon SES using the SMTP username and password you
			// specified above.
			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

			// Send the email.
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Email sent!");
		} catch (Exception ex) {
			System.out.println("The email was not sent.");
			System.out.println("Error message: " + ex.getMessage());
			return ex.getMessage();
		} finally {
			// Close and terminate the connection.
			transport.close();
		}

		return "OK";
	}

}