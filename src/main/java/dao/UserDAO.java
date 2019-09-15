package dao;

import java.util.List;

import model.User;

public interface UserDAO {
	
	public void updateUser(User user);
	
	public void insertUser(User user);
	
	public void deleteUser(User user);
	
	public User getUserByUsername(String username);
	
	public List<User> getAllUsers();
	
	public List<User> getActiveUsers();

}
