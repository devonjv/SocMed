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

@Entity
@Table(name = "SOCMED_POST")
public class Post {

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
	@Column(name = "post_type")
	private String type;

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

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_username")
	private User poster;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "group_name")
	private Group group;

	@OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
	@Column(name = "post_status")
	private String status;

	public Post() {
	}

	public Post(String text, User poster, String status) {
		/**
		 * Used to create a groupless text post.
		 */
		super();
		this.text = text;
		this.poster = poster;
		this.status = status;
		this.posted = new Date(System.currentTimeMillis());
	}

	public Post(String text, User poster, Group group, String status) {
		/**
		 * Used to create a text post in a group.
		 */
		super();
		this.text = text;
		this.poster = poster;
		this.group = group;
		this.status = status;
		this.posted = new Date(System.currentTimeMillis());
	}

	public Post(String text, User poster, String status, String type, String key) {
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

	public Post(String text, User poster, Group group, String status, String type, String key) {
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

	public Post(int id, Date posted, String text, int likes, User poster, Group group, List<Comment> comments,
			String status) {
		super();
		this.id = id;
		this.posted = posted;
		this.text = text;
		this.likes = likes;
		this.poster = poster;
		this.group = group;
		this.comments = comments;
		this.status = status;
	}

	public void addComment(Comment comment) {
		/**
		 * adds a comment.
		 */
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
		status = "BANNED";
	}
}
