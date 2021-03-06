package dao;

import java.util.List;

import model.Post;
import model.User;

public interface PostDAO {
	
	public void updatePost(Post post);
	
	public void insertPost(Post post);
	
	public void deletePost(Post post);
	
	public Post getPostById(int id);
	
	public List<Post> getAllPosts();
	
	public List<Post> getPublicPosts();
	
	public int size();
	
	public List<Post> getByUser(User user);

}
