package dao;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Comment;
import utilities.HibernateUtil;

public class CommentDAOImpl implements CommentDAO {
		
	@Override
	public void updateComment(Comment comm) {
		Session sess = HibernateUtil.getSession();
		sess.update(comm);
		sess.close();
	}
	
	@Override
	public void insertComment(Comment comm) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = sess.beginTransaction();
		sess.save(comm);
		tx.commit();
		sess.close();
		
	}
	
	@Override
	public Comment getCommentById(int id) {
		return null;
	}

	@Override
	public void deleteComment(Comment comm) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = sess.beginTransaction();
		sess.delete(comm);
		tx.commit();
		sess.close();
	}
	
	public List<Comment> getAllComments() {
		Session sess = HibernateUtil.getSession();
		Criteria crit = sess.createCriteria(Comment.class);
		List<Comment> result = crit.list();
		sess.close();
		return result;
	}

}
