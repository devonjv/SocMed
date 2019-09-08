package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Comment;
import utilities.HibernateUtil;

public class CommentDAOImpl implements CommentDAO {
	
	SessionFactory sf = HibernateUtil.getSession();
	
	@Override
	public void updateComment(Comment comm) {
		Session sess = sf.openSession();
		sess.update(comm);
		sess.close();
	}
	
	@Override
	public void insertComment(Comment comm) {
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		sess.save(comm);
		tx.commit();
		sess.close();
		
	}
	
	@Override
	public Comment getCommentById(int id) {
		return null;
	}

}
