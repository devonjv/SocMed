package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import dao.StatusDAOImpl;

@Entity
@Table(name = "SOCMED_USER_STATUS")
public class UserStatus {

	@Id
	@Column(name = "user_status_id")
	private int id;

	@Column(name = "user_status_status", nullable = false, unique = true)
	private String status;

	static private StatusDAOImpl udao = new StatusDAOImpl();

	public UserStatus() {
	}

	public UserStatus(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public boolean equals(UserStatus status) {
		return status.getStatus().equals(this.status);
	}

	@Override
	public String toString() {
		return status;
	}
	
	public static UserStatus getActiveUser() {
		return udao.getActiveUser();
	}
	
	public static UserStatus getAdminUser() {
		return udao.getAdminUser();
	}
	
	public static UserStatus getBannedUser() {
		return udao.getBannedUser();
	}

}
