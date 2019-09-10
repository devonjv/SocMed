package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import dao.StatusDAOImpl;

@Entity
@Table(name = "SOCMED_POST_STATUS")
public class PostStatus {

	@Id
	@Column(name = "post_status_id")
	private int id;

	@Column(name = "post_status_status", nullable = false, unique = true)
	private String status;

	static private StatusDAOImpl sdao = new StatusDAOImpl();

	public PostStatus() {
	}

	public PostStatus(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public boolean equals(PostStatus status) {
		return status.getStatus().equals(this.status);
	}

	@Override
	public String toString() {
		return status;
	}
	
	public static PostStatus getPublicPost() {
		return sdao.getPublicPost();
	}
	
	public static PostStatus getPrivatePost() {
		return sdao.getPrivatePost();
	}
	
	public static PostStatus getGroupPost() {
		return sdao.getGroupPost();
	}
	
	public static PostStatus getBannedPost() {
		return sdao.getBannedPost();
	}
}
