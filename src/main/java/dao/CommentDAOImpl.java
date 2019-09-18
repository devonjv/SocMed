package dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import model.Comment;

@Repository("commentDAO")
@Transactional
public class CommentDAOImpl implements CommentDAO {

	@Autowired
	private SessionFactory sf;
	
	public CommentDAOImpl() {
	}

	@Override
	public void updateComment(Comment comm) {
		sf.getCurrentSession().update(comm);
	}

	@Override
	public void insertComment(Comment comm) {
		sf.getCurrentSession().save(comm);
	}

	@Override
	public Comment getCommentById(int id) {
		return sf.getCurrentSession().get(Comment.class, id);
	}

	@Override
	public void deleteComment(Comment comm) {
		sf.getCurrentSession().delete(comm);
	}

	@Override
	public List<Comment> getAllComments() {
		return (List<Comment>) sf.getCurrentSession().createQuery("from SOCMED_COMMENT", Comment.class).list();
	}

}
