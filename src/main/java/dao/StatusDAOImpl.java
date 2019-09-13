package dao;

import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import model.GroupStatus;
import model.MessageStatus;
import model.PostStatus;
import model.UserStatus;

@Repository("statusDAO")
@Transactional
public class StatusDAOImpl implements StatusDAO {
	
	@Autowired
	private SessionFactory sf;
	
	public StatusDAOImpl() {
	}

	@Override
	public void setUserStates() {
		sf.getCurrentSession().save(new UserStatus(1, "ACTIVE"));
		sf.getCurrentSession().save(new UserStatus(2, "ADMIN"));
		sf.getCurrentSession().save(new UserStatus(3, "BANNED"));
	}

	@Override
	public void setPostStates() {
		sf.getCurrentSession().save(new PostStatus(1, "PUBLIC"));
		sf.getCurrentSession().save(new PostStatus(2, "PRIVATE"));
		sf.getCurrentSession().save(new PostStatus(3, "GROUP"));
		sf.getCurrentSession().save(new PostStatus(4, "BANNED"));
	}

	@Override
	public void setGroupStates() {
		sf.getCurrentSession().save(new GroupStatus(1, "PUBLIC"));
		sf.getCurrentSession().save(new GroupStatus(2, "PRIVATE"));
		sf.getCurrentSession().save(new GroupStatus(3, "BANNED"));
	}
	
	@Override
	public void setMessageStates() {
		sf.getCurrentSession().save(new MessageStatus(1, "VISIBLE"));
		sf.getCurrentSession().save(new MessageStatus(2, "HIDDEN"));
	}

	@Override
	public UserStatus getActiveUser() {
		return sf.getCurrentSession().get(UserStatus.class, 1);
	}

	@Override
	public UserStatus getAdminUser() {
		return sf.getCurrentSession().get(UserStatus.class, 2);
	}

	@Override
	public UserStatus getBannedUser() {
		return sf.getCurrentSession().get(UserStatus.class, 3);
	}

	@Override
	public PostStatus getPublicPost() {
		return sf.getCurrentSession().get(PostStatus.class, 1);
	}

	@Override
	public PostStatus getPrivatePost() {
		return sf.getCurrentSession().get(PostStatus.class, 2);
	}

	@Override
	public PostStatus getGroupPost() {
		return sf.getCurrentSession().get(PostStatus.class, 3);
	}

	@Override
	public PostStatus getBannedPost() {
		return sf.getCurrentSession().get(PostStatus.class, 4);
	}

	@Override
	public GroupStatus getPublicGroup() {
		return sf.getCurrentSession().get(GroupStatus.class, 1);
	}

	@Override
	public GroupStatus getPrivateGroup() {
		return sf.getCurrentSession().get(GroupStatus.class, 2);
	}

	@Override
	public GroupStatus getBannedGroup() {
		return sf.getCurrentSession().get(GroupStatus.class, 3);
	}

	@Override
	public MessageStatus getVisibleMessage() {
		return sf.getCurrentSession().get(MessageStatus.class, 1);
	}

	@Override
	public MessageStatus getHiddenMessage() {
		return sf.getCurrentSession().get(MessageStatus.class, 2);
	}
}
