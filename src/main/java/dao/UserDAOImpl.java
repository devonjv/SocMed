package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Comment;
import model.User;
import utilities.HibernateUtil;

public class UserDAOImpl implements UserDAO {
		
	@Override
	public void updateUser(User user) {
		Session sess = HibernateUtil.getSession();
		sess.update(user);
		sess.close();
	}
	
	@Override
	public void insertUser(User user) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = sess.beginTransaction();
		sess.save(user);
		tx.commit();
		sess.close();
		
	}
	
	@Override
	public User getUserByUsername(String username) {
		return null;
	}

	@Override
	public void deleteUser(User user) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = sess.beginTransaction();
		sess.delete(user);
		tx.commit();
		sess.close();
	}
	
	public List<User> getAllUsers() {
		Session sess = HibernateUtil.getSession();
		Criteria crit = sess.createCriteria(User.class);
		List<User> result = crit.list();
		sess.close();
		return result;
	}

}
