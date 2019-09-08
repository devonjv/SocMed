package dao;

import model.Comment;

public interface CommentDAO {
	
	public void updateComment(Comment comm);
	
	public void insertComment(Comment comm);
	
	public void deleteComment(Comment comm);
	
	public Comment getCommentById(int id);

}
