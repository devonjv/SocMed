package model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Component;
import mail.MailMan;
import utilities.Crypt;
import utilities.Helper;
import utilities.Temper;

@Component
@Entity
@Table(name = "SOCMED_USER")
public class User {

	private final static Logger ibis = Logger.getLogger(User.class);

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "user_username", nullable = false, unique = true)
	private String username;

	@Column(name = "user_password", nullable = false)
	private String password;

	@Column(name = "user_first_name", nullable = false)
	private String firstName;

	@Column(name = "user_last_name", nullable = false)
	private String lastName;

	/**
	 * Email addresses should be unique. One Email, one account.
	 */
	@Column(name = "user_email", nullable = false, unique = true)
	private String email;

	@Column(name = "user_picture")
	private String key;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "friend_user_username")
	private List<User> friends;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "banned_user_username")
	private List<User> banList;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "group_name")
	private List<User> groups;

	/**
	 * There should be three status states:
	 * 
	 * ACTIVE: General user, appears in the system.
	 * 
	 * ADMIN: System admin. All permissions of an ACTIVE user, but can also ban
	 * groups and users.
	 * 
	 * BANNED: Banned user. To avoid deletion of data, banned users will disappear
	 * from the system, though they will still exist in the database. this will also
	 * prevent the email from being reused to create a new account.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_status")
	private UserStatus status;

	public User() {
	}

	public User(String username, String password, String firstName, String lastName, String email) {
		/**
		 * Used to create a new User without Profile Picture
		 */
		super();
		this.username = username;
		this.password = Crypt.encryptWord(password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.status = Helper.statusService().getActiveUser();
		this.key = "default.jpg";
		Helper.userDAO().insertUser(this);
		MailMan.welcome(this);
		ibis.info(this.name() + " registered as user.\n\tUsername:\t" + username + "\n\tEmail:\t\t" + email);
	}

	public User(String username, String password, String firstName, String lastName, String email, String key) {
		/**
		 * Used to create a new User with a Profile Picture
		 */
		super();
		this.username = username;
		this.password = Crypt.encryptWord(password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.key = key;
		this.status = Helper.statusService().getActiveUser();
		Helper.userDAO().insertUser(this);
		MailMan.welcome(this);
		ibis.info(this.name() + " registered as user.\n\tUsername:\t" + username + "\n\tEmail:\t\t" + email);
	}

	public User(int id, String username, String password, String firstName, String lastName, String email, String key,
			List<User> friends, List<User> banList, List<User> groups, UserStatus status) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.key = key;
		this.friends = friends;
		this.banList = banList;
		this.groups = groups;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getKey() {
		return key;
	}

	public List<User> getFriends() {
		return friends;
	}

	public List<User> getBanList() {
		return banList;
	}

	public List<User> getGroups() {
		return groups;
	}

	public UserStatus getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + Crypt.decryptWord(password)
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", status=" + status
				+ "]";
	}

	public boolean checkPassword(String entry) {
		/**
		 * Used to test an entry against the user's password, will return whether or not
		 * they match.
		 */
		return entry.equals(Crypt.decryptWord(password));
	}

	public void makeAdmin() {
		/**
		 * Used to update user to ADMIN
		 */
		status = Helper.statusService().getAdminUser();
		Helper.userDAO().updateUser(this);
	}

	public void ban() {
		/**
		 * Used to ban users
		 */
		status = Helper.statusService().getBannedUser();
		Helper.userDAO().updateUser(this);
	}

	public void makeActive() {
		/**
		 * Used to reset status to basic user
		 */
		status = Helper.statusService().getActiveUser();
		Helper.userDAO().updateUser(this);
	}

	public boolean banned() {
		/**
		 * Tests whether the user is banned by admin.
		 */
		return status.equals(Helper.statusService().getBannedUser());
	}

	public boolean banned(Group group) {
		/**
		 * Tests whether the user is banned from a group.
		 */
		return (group.getBannedUsers().contains(this) || banned());
	}

	public boolean banned(User user) {
		/**
		 * tests whether the user is blocked by an entered user.
		 */
		return (user.getBanList().contains(this) || banned());
	}

	public String name() {
		return firstName + " " + lastName;
	}

	public void block(User user) {
		/**
		 * blocks the entered user.
		 */
		banList.add(user);
		Helper.userDAO().updateUser(this);
	}

	public void changePassword(String newPassword) {
		/**
		 * Used to change the password.
		 */
		password = Crypt.encryptWord(newPassword);
		MailMan.change(this);
		Helper.userDAO().updateUser(this);
	}

	public void changeFirstName(String newName) {
		/**
		 * Used to change the first name.
		 */
		firstName = newName;
		MailMan.change(this);
		Helper.userDAO().updateUser(this);
	}

	public void changeLastName(String newName) {
		/**
		 * Used to change the last name.
		 */
		lastName = newName;
		MailMan.change(this);
		Helper.userDAO().updateUser(this);
	}

	public void changeEmail(String newEmail) {
		/**
		 * Used to change the email.
		 */
		email = newEmail;
		MailMan.change(this);
		Helper.userDAO().updateUser(this);
	}

	public void changePicture(String key) {
		this.key = key;
		Helper.userDAO().updateUser(this);
		ibis.info(this.name() + " updated their profile picture");
		MailMan.change(this);
	}

	public void forgotPassword() {
		/**
		 * Used to reset the password to a temporary password, and send a message to the
		 * user.
		 */
		password = Crypt.encryptWord("@TMP_" + Temper.chunk(7));
		MailMan.newPassword(this);
		Helper.userDAO().updateUser(this);
	}

}
