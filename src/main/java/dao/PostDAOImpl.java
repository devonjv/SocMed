package dao;

import java.util.List;

import org.hibernate.Criteria;
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
		Session sess = HibernateUtil.getSession();
		Transaction tx = sess.beginTransaction();
		sess.delete(post);
		tx.commit();
		sess.close();
	}
	
	public List<Post> getAllPosts() {
		Session sess = HibernateUtil.getSession();
		Criteria crit = sess.createCriteria(Post.class);
		List<Post> result = crit.list();
		sess.close();
		return result;
	}

}
