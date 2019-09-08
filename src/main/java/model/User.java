package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import utilities.Crypt;

@Entity
@Table(name = "SOCMED_USER")
public class User {

	@Id
	@Column(name = "user_username")
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

	@ManyToMany(mappedBy = "friends", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_username")
	private List<User> friends;

	@ManyToMany(mappedBy = "banList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_username")
	private List<User> banList;

	@ManyToMany(mappedBy = "members", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "group_name")
	private List<User> groups;

	@OneToMany(mappedBy = "poster", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "post_id")
	private List<Post> posts;

	@OneToMany(mappedBy = "sender", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "message_id")
	private List<Message> sentMessages;

	@OneToMany(mappedBy = "reciever", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "message_id")
	private List<Message> receivedMessages;

	/**
	 * There should be three status states:
	 * 
	 * ACVITE: General user, appears in the system.
	 * 
	 * ADMIN: System admin. All permissions of an ACTIVE user, but can also ban
	 * groups and users.
	 * 
	 * BANNED: Banned user. To avoid deletion of data, banned users will disappear
	 * from the system, though they will still exist in the database. this will also
	 * prevent the email from being reused to create a new account.
	 */
	@Column(name = "user_status", nullable = false)
	private String status;

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
		this.status = "ACTIVE";
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
		this.status = "ACTIVE";
	}

	public User(String username, String password, String firstName, String lastName, String email, String key,
			List<User> friends, List<User> banList, List<User> groups, List<Post> posts, List<Message> sentMessages,
			List<Message> receivedMessages, String status) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.key = key;
		this.friends = friends;
		this.banList = banList;
		this.groups = groups;
		this.posts = posts;
		this.sentMessages = sentMessages;
		this.receivedMessages = receivedMessages;
		this.status = status;
	}

	public String getUsername() {
		return username;
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

	public List<Post> getPosts() {
		return posts;
	}

	public List<Message> getSentMessages() {
		return sentMessages;
	}

	public List<Message> getReceivedMessages() {
		return receivedMessages;
	}

	public String getStatus() {
		return status;
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
		status = "ADMIN";
	}

	public void ban() {
		/**
		 * Used to ban users
		 */
		status = "BANNED";
	}

	public boolean banned() {
		/**
		 * Tests whether the user is banned by admin.
		 */
		return status.equals("BANNED");
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
}
