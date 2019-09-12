package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SOCMED_POST_STATUS")
public class PostStatus {

	@Id
	@Column(name = "post_status_id")
	private int id;

	@Column(name = "post_status_status", nullable = false, unique = true)
	private String status;

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
}
