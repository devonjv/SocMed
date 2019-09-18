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
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Component;
import utilities.Helper;

@Component
@Entity
@Table(name = "SOCMED_GROUP")
public class Group {

	private final static Logger ibis = Logger.getLogger(User.class);
	
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

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value=FetchMode.SUBSELECT)
	@JoinColumn(name = "member_user_username")
	private List<User> members;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value=FetchMode.SUBSELECT)
	@JoinColumn(name = "admin_user_username")
	private List<User> admins;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value=FetchMode.SUBSELECT)
	@JoinColumn(name = "banned_user_username")
	private List<User> bannedUsers;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value=FetchMode.SUBSELECT)
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
		Helper.groupDAO().insertGroup(this);
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

	public GroupStatus getStatus() {
		return status;
	}

	public void addUser(User user) {
		members.add(user);
		ibis.info(user.name() + " added to group " + name);
	}

	public void addAdmin(User user) {
		admins.add(user);
		ibis.info(user.name() + " promoted to admin of " + name);
	}

	public void banUser(User user) {
		bannedUsers.add(user);
		ibis.info(user.name() + " banned from group " + name);
	}

	public void ban() {
		status = Helper.statusService().getBannedGroup();
		ibis.info("Group " + name + " banned");
	}

	public int members() {
		return members.size();
	}

	public void post(Post post) {
		posts.add(post);
		ibis.info(post.getPoster().name() + " posted to group " + name);
	}
}
