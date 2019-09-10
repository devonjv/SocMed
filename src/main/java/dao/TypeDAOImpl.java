package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import model.PostType;
import utilities.HibernateUtil;

public class TypeDAOImpl implements TypeDAO {

	@Override
	public void setPostTypes() {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(new PostType(1, "TEXT"));
		ses.save(new PostType(2, "PICTURE"));
		ses.save(new PostType(3, "VIDEO"));
		tx.commit();

	}

	@Override
	public PostType getTextPost() {
		Session ses = HibernateUtil.getSession();
		return ses.get(PostType.class, 1);
	}

	@Override
	public PostType getPicturePost() {
		Session ses = HibernateUtil.getSession();
		return ses.get(PostType.class, 2);
	}

	@Override
	public PostType getVideoPost() {
		Session ses = HibernateUtil.getSession();
		return ses.get(PostType.class, 3);
	}

}
