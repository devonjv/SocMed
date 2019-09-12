package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SOCMED_GROUP_STATUS")
public class GroupStatus {

	@Id
	@Column(name = "group_status_id")
	private int id;

	@Column(name = "group_status_status", nullable = false, unique = true)
	private String status;

	public GroupStatus() {
	}

	public GroupStatus(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public boolean equals(GroupStatus status) {
		return status.getStatus().equals(this.status);
	}

	@Override
	public String toString() {
		return status;
	}
}
