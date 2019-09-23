package mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.User;
import utilities.Crypt;

public class MailMan {
	private static final String host = "smtp.live.com";
	private static final String username = "vndlMessaging@hotmail.com";
	private static final String password = "Vndl_6842";
	private static final String from = "vndlMessaging@hotmail.com";
	private static String to;
	private static String subject;
	private static String message;
	private static String tail = "\r\rThe faceit Team\r\r(This message was sent automatically, replies will not be seen.)";

	private MailMan() {
		super();
	}

	private static void send(User user) {
		to = user.getEmail();
		Properties pro = new Properties();
		pro.put("mail.smtp.auth", "true");
		pro.put("mail.smtp.starttls.enable", "true");
		pro.put("mail.smtp.host", host);
		Authorizor auth = new Authorizor(username, password);
		Session ses = Session.getInstance(pro, auth);
		Message herm = new MimeMessage(ses);
		try {
			herm.setFrom(new InternetAddress(from));
			herm.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			herm.setSubject(subject);
			herm.setText(message + tail);
			herm.saveChanges();
			Transport bif = ses.getTransport("smtp");
			bif.connect(host, username, password);
			bif.sendMessage(herm, herm.getAllRecipients());
			bif.close();
			message = "";
			to = "";
			subject="";
		} catch (Exception ex) {
			System.out.println("Email not real");
		}
	}

	public static void welcome(User user) {
		subject ="Welcome to faceit";
		message = "Hello " + user.name()
				+ ",\r\rWelcome to faceit!\nThank you for registering, and we look forward to your future posts.";
		send(user);
	}

	public static void newPassword(User user) {
		subject ="Your temporary password";
		message = "Hello " + user.name() + ",\r Your temporary password is:\t\"" + Crypt.decryptWord(user.getPassword())
				+ "\",\r\rPlease use this to sign in and reset your password.";
		send(user);
	}

	public static void change(User user) {
		subject ="Alert from faceit";
		message = "Hello " + user.name()
				+ ",\r\rThis email has been sent to let you know that your personal information has been changed.\rIf you are not the one to make these changes, you may want to confirm your account security.";
		send(user);
	}
}
