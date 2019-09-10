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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;

@Entity
@Table(name = "SOCMED_POST")
public class Post {
	
	protected final static Logger ibis = Logger.getLogger(User.class);

	@Id
	@Column(name = "post_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/**
	 * There should be three types:
	 * 
	 * TEXT: Standard text post.
	 * 
	 * PICTURE: Post with a picture.
	 * 
	 * VIDEO: Link to a YouTube video
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "post_type")
	private PostType type;

	/**
	 * Link to either a picture or YouTube video.
	 */
	@Column(name = "post_key")
	private String key;

	@Column(name = "post_posted", nullable = false)
	private Date posted;

	@Column(name = "post_text")
	private String text;

	@Column(name = "post_likes")
	private int likes;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_username")
	private User poster;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "group_name")
	private Group group;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "comment_id")
	private List<Comment> comments;

	/**
	 * The status states should be:
	 * 
	 * PUBLIC: Anyone can view.
	 * 
	 * PRIVATE: Only friends of poster can view.
	 * 
	 * GROUP: Only members of the group it was posted in can view.
	 * 
	 * BANNED: Post banned. Hidden from system. Can still be viewed by original
	 * poster.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "post_status")
	private PostStatus status;

	public Post() {
	}

	public Post(String text, User poster, PostStatus status) {
		/**
		 * Used to create a groupless text post.
		 */
		super();
		this.text = text;
		this.poster = poster;
		this.status = status;
		this.posted = new Date(System.currentTimeMillis());
		this.type=PostType.getTextPost();
	}

	public Post(String text, User poster, Group group, PostStatus status) {
		/**
		 * Used to create a text post in a group.
		 */
		super();
		this.text = text;
		this.poster = poster;
		this.group = group;
		this.status = status;
		this.posted = new Date(System.currentTimeMillis());
		this.type=PostType.getTextPost();
	}

	public Post(String text, User poster, PostStatus status, PostType type, String key) {
		/**
		 * Used to create a groupless picture or video post.
		 */
		super();
		this.text = text;
		this.poster = poster;
		this.status = status;
		this.type = type;
		this.key = key;
		this.posted = new Date(System.currentTimeMillis());
	}

	public Post(String text, User poster, Group group, PostStatus status, PostType type, String key) {
		/**
		 * Used to create a picture or video post in a group.
		 */
		super();
		this.text = text;
		this.poster = poster;
		this.group = group;
		this.status = status;
		this.type = type;
		this.key = key;
		this.posted = new Date(System.currentTimeMillis());
	}

	public Post(int id, PostType type, String key, Date posted, String text, int likes, User poster, Group group,
			List<Comment> comments, PostStatus status) {
		super();
		this.id = id;
		this.type = type;
		this.key = key;
		this.posted = posted;
		this.text = text;
		this.likes = likes;
		this.poster = poster;
		this.group = group;
		this.comments = comments;
		this.status = status;
	}

	public PostType getType() {
		return type;
	}

	public String getKey() {
		return key;
	}

	public Date getPosted() {
		return posted;
	}

	public String getText() {
		return text;
	}

	public int getLikes() {
		return likes;
	}

	public User getPoster() {
		return poster;
	}

	public Group getGroup() {
		return group;
	}

	public List<Comment> getComments() {
		comments = (List<Comment>) Hibernate.unproxy(comments);
		return comments;
	}

	public PostStatus getStatus() {
		return status;
	}

	public void addComment(Comment comment) {
		/**
		 * adds a comment.
		 */
		comments = (List<Comment>) Hibernate.unproxy(comments);
		comments.add(comment);
	}

	public void like() {
		/**
		 * Increases the number of likes
		 */
		likes++;
	}

	public void ban() {
		/**
		 * bans the post
		 */
		status = PostStatus.getBannedPost();
	}
}
