package dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sf;

	public UserDAOImpl() {
	}

	@Override
	public void updateUser(User user) {
		sf.getCurrentSession().update(user);
	}

	@Override
	public void insertUser(User user) {
		sf.getCurrentSession().save(user);
	}

	@Override
	public User getUserByUsername(String username) {
		return sf.getCurrentSession().createQuery("from User where username=:temp", User.class)
				.setParameter("temp", username).getSingleResult();
	}

	@Override
	public void deleteUser(User user) {
		sf.getCurrentSession().delete(user);
	}

	public List<User> getAllUsers() {
		return (List<User>) sf.getCurrentSession().createQuery("from User", User.class).list();
	}

}
