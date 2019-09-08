package dao;

import model.Group;

public interface GroupDAO {
	
	public void updateGroup(Group group);
	
	public void insertGroup(Group group);
	
	public void deleteGroup(Group group);
	
	public Group getGroupByName(String name);

}
