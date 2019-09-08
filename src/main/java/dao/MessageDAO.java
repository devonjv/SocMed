package dao;

import model.Message;

public interface MessageDAO {
	
	public void updateMessage(Message msg);
	
	public void insertMessage(Message msg);
	
	public void deleteMessage(Message msg);
	
	public Message getMessageById(int id);

}
