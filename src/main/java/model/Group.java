package model;

import java.sql.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SOCMED_GROUP")
public class Group {

	@Id
	@Column(name = "group_name")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String name;

	@Column(name = "group_created", nullable = false)
	private Date posted;

	@Column(name = "group_description", nullable = false)
	private String description;

	@ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_username")
	private List<User> members;

	@ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_username")
	private List<User> admins;
	
	@ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_username")
	private List<User> bannedUsers;

	@OneToMany(mappedBy = "group", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "post_id")
	private List<Post> posts;

	/**
	 * Status types should be:
	 * 
	 * PUBLIC: Anyone can join.
	 * 
	 * PRIVATE: Invitation by group admin only.
	 * 
	 * BANNED: Group shut down by system admin.
	 */
	@Column(name = "group_status")
	private String status;

	public Group() {
	}

	public Group(String name, String description, User creator) {
		/**
		 * Used to create a new Group
		 */
		super();
		this.name = name;
		this.posted = new Date(System.currentTimeMillis());
		this.description = description;
		this.admins.add(creator);
		this.members.add(creator);
	}
	
	public Group(String name, Date posted, String description, List<User> members, List<User> admins,
			List<User> bannedUsers, List<Post> posts, String status) {
		super();
		this.name = name;
		this.posted = posted;
		this.description = description;
		this.members = members;
		this.admins = admins;
		this.bannedUsers = bannedUsers;
		this.posts = posts;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public Date getPosted() {
		return posted;
	}

	public String getDescription() {
		return description;
	}

	public List<User> getMembers() {
		return members;
	}

	public List<User> getAdmins() {
		return admins;
	}

	public List<User> getBannedUsers() {
		return bannedUsers;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public String getStatus() {
		return status;
	}

	public void addUser(User user) {
		members.add(user);
	}

	public void addAdmin(User user) {
		admins.add(user);
	}
	
	public void ban() {
		status="BANNED";
	}
	
	public int members() {
		return members.size();
	}
}
