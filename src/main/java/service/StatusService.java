package service;

import model.GroupStatus;
import model.MessageStatus;
import model.PostStatus;
import model.UserStatus;

public interface StatusService {

	public GroupStatus getPublicGroup();

	public GroupStatus getPrivateGroup();

	public GroupStatus getBannedGroup();

	public PostStatus getPublicPost();

	public PostStatus getPrivatePost();

	public PostStatus getGroupPost();

	public PostStatus getBannedPost();

	public UserStatus getActiveUser();

	public UserStatus getAdminUser();

	public UserStatus getBannedUser();

	public MessageStatus getVisibleMessage();

	public MessageStatus getHiddenMessage();

}
