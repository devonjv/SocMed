package service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import dao.StatusDAO;
import model.GroupStatus;
import model.MessageStatus;
import model.PostStatus;
import model.UserStatus;

@Service("statusService")
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusDAO statusDAO;

	private GroupStatus publicGroup;

	private GroupStatus privateGroup;

	private GroupStatus bannedGroup;

	private PostStatus publicPost;

	private PostStatus privatePost;

	private PostStatus groupPost;

	private PostStatus bannedPost;

	private UserStatus activeUser;

	private UserStatus adminUser;

	private UserStatus bannedUser;

	private MessageStatus visibleMessage;

	private MessageStatus hiddenMessage;

	public StatusServiceImpl() {
	}

	@Autowired
	public StatusServiceImpl(StatusDAO statusDAO) {
		this.statusDAO = statusDAO;
		publicGroup = statusDAO.getPublicGroup();
		privateGroup = statusDAO.getPrivateGroup();
		bannedGroup = statusDAO.getBannedGroup();
		publicPost = statusDAO.getPublicPost();
		privatePost = statusDAO.getPrivatePost();
		groupPost = statusDAO.getGroupPost();
		bannedPost = statusDAO.getBannedPost();
		activeUser = statusDAO.getActiveUser();
		adminUser = statusDAO.getAdminUser();
		bannedUser = statusDAO.getBannedUser();
		visibleMessage = statusDAO.getVisibleMessage();
		hiddenMessage = statusDAO.getHiddenMessage();
	}

	@Override
	public GroupStatus getPublicGroup() {
		return publicGroup;
	}

	@Override
	public GroupStatus getPrivateGroup() {
		return privateGroup;
	}

	@Override
	public GroupStatus getBannedGroup() {
		return bannedGroup;
	}

	@Override
	public PostStatus getPublicPost() {
		return publicPost;
	}

	@Override
	public PostStatus getPrivatePost() {
		return privatePost;
	}

	@Override
	public PostStatus getGroupPost() {
		return groupPost;
	}

	@Override
	public PostStatus getBannedPost() {
		return bannedPost;
	}

	@Override
	public UserStatus getActiveUser() {
		return activeUser;
	}

	@Override
	public UserStatus getAdminUser() {
		return adminUser;
	}

	@Override
	public UserStatus getBannedUser() {
		return bannedUser;
	}

	@Override
	public MessageStatus getVisibleMessage() {
		return visibleMessage;
	}

	@Override
	public MessageStatus getHiddenMessage() {
		return hiddenMessage;
	}
}
