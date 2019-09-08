package mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Authorizor extends Authenticator {

	private String uname;
	private String pword;
	private PasswordAuthentication auth;

	public Authorizor(String username, String password) {
		super();
		this.uname = username;
		this.pword = password;
		auth = new PasswordAuthentication(uname, pword);
	}

	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		return auth;
	}
}
