package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Comment;
import model.Post;
import utilities.HibernateUtil;

public class PostDAOImpl implements PostDAO {
		
	@Override
	public void updatePost(Post post) {
		Session sess = HibernateUtil.getSession();
		sess.update(post);
		sess.close();
	}
	
	@Override
	public void insertPost(Post post) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = sess.beginTransaction();
		sess.save(post);
		tx.commit();
		sess.close();
		
	}
	
	@Override
	public Post getPostById(int id) {
		return null;
	}

	@Override
	public void deletePost(Post post) {
		// TODO Auto-generated method stub
		
	}

}
