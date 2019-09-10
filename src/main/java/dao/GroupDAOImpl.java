package dao;

import java.util.List;

import org.hibernate.Criteria;
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
		Session sess = HibernateUtil.getSession();
		Transaction tx = sess.beginTransaction();
		sess.delete(group);
		tx.commit();
		sess.close();
	}
	
	public List<Group> getAllGroups() {
		Session sess = HibernateUtil.getSession();
		Criteria crit = sess.createCriteria(Group.class);
		List<Group> result = crit.list();
		sess.close();
		return result;
	}
}
