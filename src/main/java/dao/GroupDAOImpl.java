package dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import model.Group;
import model.User;
import utilities.Helper;

@Repository("groupDAO")
@Transactional
public class GroupDAOImpl implements GroupDAO {

	@Autowired
	private SessionFactory sf;

	@Override
	public void updateGroup(Group group) {
		sf.getCurrentSession().update(group);
	}

	@Override
	public void insertGroup(Group group) {
		sf.getCurrentSession().save(group);
	}

	@Override
	public Group getGroupByName(String name) {
		return sf.getCurrentSession().createQuery("from Group where name=:temp", Group.class)
				.setParameter("temp", name).getSingleResult();
	}

	@Override
	public void deleteGroup(Group group) {
		sf.getCurrentSession().delete(group);
	}

	@Override
	public List<Group> getAllGroups() {
		return (List<Group>) sf.getCurrentSession().createQuery("from Group", Group.class).list();
	}

	@Override
	public List<Group> getAllPublicGroups() {
		/**
		 * Returns any public, non-banned group
		 */
		return (List<Group>) sf.getCurrentSession().createQuery("from Group where status!=:temp", Group.class)
				.setParameter("temp", Helper.statusService().getBannedGroup()).list();
	}

}
