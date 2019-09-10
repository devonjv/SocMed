package dao;

import model.GroupStatus;
import model.MessageStatus;
import model.PostStatus;
import model.UserStatus;

public interface StatusDAO {
	
	public void setUserStates();
	
	public void setPostStates();
	
	public void setGroupStates();
	
	public void setMessageStates();
	
	public UserStatus getActiveUser();
	
	public UserStatus getAdminUser();
	
	public UserStatus getBannedUser();
	
	public PostStatus getPublicPost();
	
	public PostStatus getPrivatePost();
	
	public PostStatus getGroupPost();
	
	public PostStatus getBannedPost();
	
	public GroupStatus getPublicGroup();
	
	public GroupStatus getPrivateGroup();
	
	public GroupStatus getBannedGroup();
	
	public MessageStatus getVisibleMessage();
	
	public MessageStatus getHiddenMessage();
}
