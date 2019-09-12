package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SOCMED_MESSAGE_STATUS")
public class MessageStatus {

	@Id
	@Column(name = "message_status_id")
	private int id;

	@Column(name = "message_status_status", nullable = false, unique = true)
	private String status;

	public MessageStatus() {
	}

	public MessageStatus(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public boolean equals(MessageStatus status) {
		return status.getStatus().equals(this.status);
	}

	@Override
	public String toString() {
		return status;
	}
}
