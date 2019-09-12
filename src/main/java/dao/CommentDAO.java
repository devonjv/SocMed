package dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Comment;

@Repository
public class CommentDAO {
	
	@Autowired
	private SessionFactory sf;

	public CommentDAO() {
	}

	public void insert(Comment comm) {
		sf.getCurrentSession().save(comm);
	}

	public void update(Comment comm) {
		sf.getCurrentSession().update(comm);
	}

	public Comment selectById(int id) {
		return sf.getCurrentSession().get(Comment.class, id);
	}

	public List<Comment> selectAll() {
		return sf.getCurrentSession()
				.createQuery("from Comment", Comment.class).list();
	}

}
