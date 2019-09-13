package dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import model.Post;

@Repository("postDAO")
@Transactional
public class PostDAOImpl implements PostDAO {

	@Autowired
	private SessionFactory sf;

	@Override
	public void updatePost(Post post) {
		sf.getCurrentSession().update(post);
	}

	@Override
	public void insertPost(Post post) {
		sf.getCurrentSession().save(post);
	}

	@Override
	public Post getPostById(int id) {
		return sf.getCurrentSession().get(Post.class, id);
	}

	@Override
	public void deletePost(Post post) {
		sf.getCurrentSession().delete(post);
	}

	public List<Post> getAllPosts() {
		return (List<Post>) sf.getCurrentSession().createQuery("from SOCMED_POST", Post.class).list();
	}

}
