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
import javax.persistence.Table;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import utilities.Helper;

@Component
@Entity
@Table(name = "SOCMED_COMMENT")
public class Comment {

	private final static Logger ibis = Logger.getLogger(User.class);

	@Id
	@Column(name = "comment_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "comment_text", nullable = false)
	private String text;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "user_username")
	private User poster;

	@Column(name = "comment_posted")
	private Date posted;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "post_id")
	private Post post;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "super_comment_id")
	private Comment superComment;

	@Column(name = "comment_likes")
	private int likes;

	public Comment() {
	}

	public Comment(User poster, String text, Post post) {
		/**
		 * Used to create a direct to post comment. Adds this comment to the comments
		 * list of the post.
		 */
		super();
		this.poster = poster;
		this.text = text;
		this.post = post;
		this.posted = new Date(System.currentTimeMillis());
		Helper.commentDAO().insertComment(this);
		ibis.info("Comment added to post #" + post.getId());
	}

	public Comment(User poster, String text, Comment superComment) {
		/**
		 * Used to create a sub-comment. Adds this comment to the subcomments list of
		 * its super.
		 */
		super();
		this.poster = poster;
		this.text = text;
		this.superComment = superComment;
		this.post = superComment.getPost();
		this.posted = new Date(System.currentTimeMillis());
		Helper.commentDAO().insertComment(this);
		ibis.info("Comment added to comment #" + superComment.getId());
	}

	public Comment(User poster, int id, String text, Date posted, Post post, Comment superComment,
			List<Comment> subcomments, int likes) {
		super();
		this.poster = poster;
		this.id = id;
		this.text = text;
		this.posted = posted;
		this.post = post;
		this.superComment = superComment;
		this.likes = likes;
	}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public User getPoster() {
		return poster;
	}

	public Date getPosted() {
		return posted;
	}

	public Post getPost() {
		return post;
	}

	public Comment getSuperComment() {
		return superComment;
	}

	public int getLikes() {
		return likes;
	}

	public void like() {
		/**
		 * Increments the number of likes.
		 */
		likes++;
		ibis.info("Comment #" + id + " liked");
	}
}
