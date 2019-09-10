package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.GroupStatus;
import model.MessageStatus;
import model.PostStatus;
import model.UserStatus;
import utilities.HibernateUtil;

public class StatusDAOImpl implements StatusDAO {

	@Override
	public void setUserStates() {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(new UserStatus(1, "ACTIVE"));
		ses.save(new UserStatus(2, "ADMIN"));
		ses.save(new UserStatus(3, "BANNED"));
		tx.commit();
	}

	@Override
	public void setPostStates() {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(new PostStatus(1, "PUBLIC"));
		ses.save(new PostStatus(2, "PRIVATE"));
		ses.save(new PostStatus(3, "GROUP"));
		ses.save(new PostStatus(4, "BANNED"));
		tx.commit();
	}

	@Override
	public void setGroupStates() {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(new GroupStatus(1, "PUBLIC"));
		ses.save(new GroupStatus(2, "PRIVATE"));
		ses.save(new GroupStatus(3, "BANNED"));
		tx.commit();
	}
	
	@Override
	public void setMessageStates() {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(new MessageStatus(1, "VISIBLE"));
		ses.save(new MessageStatus(2, "HIDDEN"));
		tx.commit();
	}

	@Override
	public UserStatus getActiveUser() {
		Session ses = HibernateUtil.getSession();
		return ses.get(UserStatus.class, 1);
	}

	@Override
	public UserStatus getAdminUser() {
		Session ses = HibernateUtil.getSession();
		return ses.get(UserStatus.class, 2);
	}

	@Override
	public UserStatus getBannedUser() {
		Session ses = HibernateUtil.getSession();
		return ses.get(UserStatus.class, 3);
	}

	@Override
	public PostStatus getPublicPost() {
		Session ses = HibernateUtil.getSession();
		return ses.get(PostStatus.class, 1);
	}

	@Override
	public PostStatus getPrivatePost() {
		Session ses = HibernateUtil.getSession();
		return ses.get(PostStatus.class, 2);
	}

	@Override
	public PostStatus getGroupPost() {
		Session ses = HibernateUtil.getSession();
		return ses.get(PostStatus.class, 3);
	}

	@Override
	public PostStatus getBannedPost() {
		Session ses = HibernateUtil.getSession();
		return ses.get(PostStatus.class, 4);
	}

	@Override
	public GroupStatus getPublicGroup() {
		Session ses = HibernateUtil.getSession();
		return ses.get(GroupStatus.class, 1);
	}

	@Override
	public GroupStatus getPrivateGroup() {
		Session ses = HibernateUtil.getSession();
		return ses.get(GroupStatus.class, 2);
	}

	@Override
	public GroupStatus getBannedGroup() {
		Session ses = HibernateUtil.getSession();
		return ses.get(GroupStatus.class, 3);
	}

	@Override
	public MessageStatus getVisibleMessage() {
		Session ses = HibernateUtil.getSession();
		return ses.get(MessageStatus.class, 1);
	}

	@Override
	public MessageStatus getHiddenMessage() {
		Session ses = HibernateUtil.getSession();
		return ses.get(MessageStatus.class, 2);
	}

}
