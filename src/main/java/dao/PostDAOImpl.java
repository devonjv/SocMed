package dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import model.Post;
import utilities.Helper;

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

	@Override
	public List<Post> getAllPosts() {
		return (List<Post>) sf.getCurrentSession().createQuery("from Post", Post.class).list();
	}

	@Override
	public List<Post> getPublicPosts() {
		return (List<Post>) sf.getCurrentSession().createQuery("from Post where status=:temp", Post.class)
				.setParameter("temp", Helper.statusService().getPublicPost()).list();
	}

	@Override
	public int size() {
		/**
		 * Returns how many posts exist in the database.
		 */
		return sf.getCurrentSession().createQuery("from Post", Post.class).list().size();
	}

}
