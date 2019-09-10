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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;

@Entity
@Table(name = "SOCMED_GROUP")
public class Group {

	protected final static Logger ibis = Logger.getLogger(User.class);

	@Id
	@Column(name = "group_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "group_name", nullable = false, unique = true)
	private String name;

	@Column(name = "group_created", nullable = false)
	private Date posted;

	@Column(name = "group_description", nullable = false)
	private String description;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "member_user_username")
	private List<User> members;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "admin_user_username")
	private List<User> admins;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "banned_user_username")
	private List<User> bannedUsers;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "group_status")
	private GroupStatus status;

	public Group() {
	}

	public Group(String name, String description, User creator, GroupStatus status) {
		/**
		 * Used to create a new Group
		 */
		super();
		this.name = name;
		this.posted = new Date(System.currentTimeMillis());
		this.description = description;
		this.status = status;
		this.admins.add(creator);
		this.members.add(creator);
		ibis.info(creator.name() + " created group: " + name);
	}

	public Group(int id, String name, Date posted, String description, List<User> members, List<User> admins,
			List<User> bannedUsers, List<Post> posts, GroupStatus status) {
		super();
		this.id = id;
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
		members = (List<User>) Hibernate.unproxy(members);
		return members;
	}

	public List<User> getAdmins() {
		admins = (List<User>) Hibernate.unproxy(admins);
		return admins;
	}

	public List<User> getBannedUsers() {
		bannedUsers = (List<User>) Hibernate.unproxy(bannedUsers);
		return bannedUsers;
	}

	public List<Post> getPosts() {
		posts = (List<Post>) Hibernate.unproxy(posts);
		return posts;
	}

	public GroupStatus getStatus() {
		return status;
	}

	public void addUser(User user) {
		members = (List<User>) Hibernate.unproxy(members);
		members.add(user);
		ibis.info(user.name() + " added to group " + name);
	}

	public void addAdmin(User user) {
		admins = (List<User>) Hibernate.unproxy(admins);
		admins.add(user);
		ibis.info(user.name() + " promoted to admin of " + name);
	}

	public void banUser(User user) {
		bannedUsers = (List<User>) Hibernate.unproxy(bannedUsers);
		bannedUsers.add(user);
		ibis.info(user.name() + " banned from group " + name);
	}

	public void ban() {
		status = GroupStatus.getBannedGroup();
		ibis.info("Group " + name + " banned");
	}

	public int members() {
		members = (List<User>) Hibernate.unproxy(members);
		return members.size();
	}

	public void post(Post post) {
		posts = (List<Post>) Hibernate.unproxy(posts);
		posts.add(post);
		ibis.info(post.getPoster().name() + " posted to group " + name);
	}
}
