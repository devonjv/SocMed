package dao;

import java.util.List;

import model.Comment;

public interface CommentDAO {
	
	public void updateComment(Comment comm);
	
	public void insertComment(Comment comm);
	
	public void deleteComment(Comment comm);
	
	public Comment getCommentById(int id);
	
	public List<Comment> getAllComments();

}
