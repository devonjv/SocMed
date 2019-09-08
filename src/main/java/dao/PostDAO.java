package dao;

import model.Post;

public interface PostDAO {
	
	public void updatePost(Post post);
	
	public void insertPost(Post post);
	
	public void deletePost(Post post);
	
	public Post getPostById(int id);

}
