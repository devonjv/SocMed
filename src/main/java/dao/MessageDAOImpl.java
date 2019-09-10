package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Comment;
import model.Message;
import utilities.HibernateUtil;

public class MessageDAOImpl implements MessageDAO {
		
	@Override
	public void updateMessage(Message msg) {
		Session sess = HibernateUtil.getSession();
		sess.update(msg);
		sess.close();
	}
	
	@Override
	public void insertMessage(Message msg) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = sess.beginTransaction();
		sess.save(msg);
		tx.commit();
		sess.close();
		
	}
	
	@Override
	public Message getMessageById(int id) {
		return null;
	}

	@Override
	public void deleteMessage(Message msg) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = sess.beginTransaction();
		sess.delete(msg);
		tx.commit();
		sess.close();
	}
	
	public List<Message> getAllMessages() {
		Session sess = HibernateUtil.getSession();
		Criteria crit = sess.createCriteria(Message.class);
		List<Message> result = crit.list();
		sess.close();
		return result;
	}

}
