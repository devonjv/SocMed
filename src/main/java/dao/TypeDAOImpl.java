package dao;

import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import model.PostType;

@Repository("typeDAO")
@Transactional
public class TypeDAOImpl implements TypeDAO {

	@Autowired
	private SessionFactory sf;

	public TypeDAOImpl() {
	}

	@Override
	public void setPostTypes() {
		sf.getCurrentSession().save(new PostType(1, "TEXT"));
		sf.getCurrentSession().save(new PostType(2, "PICTURE"));
		sf.getCurrentSession().save(new PostType(3, "VIDEO"));
	}

	@Override
	public PostType getTextPost() {
		return sf.getCurrentSession().get(PostType.class, 1);
	}

	@Override
	public PostType getPicturePost() {
		return sf.getCurrentSession().get(PostType.class, 2);
	}

	@Override
	public PostType getVideoPost() {
		return sf.getCurrentSession().get(PostType.class, 3);
	}

}
