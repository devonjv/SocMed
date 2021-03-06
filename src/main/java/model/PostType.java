package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SOCMED_POST_TYPE")
public class PostType {
	@Id
	@Column(name = "post_type_id")
	private int id;

	@Column(name = "post_type_type", nullable = false, unique = true)
	private String type;

	public PostType() {
	}

	public PostType(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public boolean equals(PostType type) {
		return type.getType().equals(this.type);
	}

	@Override
	public String toString() {
		return type;
	}
}
