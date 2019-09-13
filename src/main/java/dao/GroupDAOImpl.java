package dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import model.Group;

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
		return sf.getCurrentSession().createQuery("from SOCMED_GROUP where group_name=:temp", Group.class)
				.setParameter("temp", name).getSingleResult();
	}

	@Override
	public void deleteGroup(Group group) {
		sf.getCurrentSession().delete(group);
	}

	public List<Group> getAllGroups() {
		return (List<Group>) sf.getCurrentSession().createQuery("from SOCMED_GROUP", Group.class).list();
	}

}
