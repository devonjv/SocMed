package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Comment;
import model.Group;
import utilities.HibernateUtil;

public class GroupDAOImpl implements GroupDAO {

	@Override
	public void updateGroup(Group group) {
		Session sess = HibernateUtil.getSession();
		sess.update(group);
		sess.close();
	}

	@Override
	public void insertGroup(Group group) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = sess.beginTransaction();
		sess.save(group);
		tx.commit();
		sess.close();

	}

	@Override
	public Group getGroupByName(String name) {
		return null;
	}

	@Override
	public void deleteGroup(Group group) {
		// TODO Auto-generated method stub
		
	}

}
