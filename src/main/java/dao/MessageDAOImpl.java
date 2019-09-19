package dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import model.Message;

@Repository("messageDAO")
@Transactional
public class MessageDAOImpl implements MessageDAO {

	@Autowired
	private SessionFactory sf;

	@Override
	public void updateMessage(Message msg) {
		sf.getCurrentSession().update(msg);
	}

	@Override
	public void insertMessage(Message msg) {
		sf.getCurrentSession().save(msg);
	}

	@Override
	public Message getMessageById(int id) {
		return sf.getCurrentSession().get(Message.class, id);
	}

	@Override
	public void deleteMessage(Message msg) {
		sf.getCurrentSession().delete(msg);
	}

	public List<Message> getAllMessages() {
		return (List<Message>) sf.getCurrentSession().createQuery("from Message", Message.class).list();
	}

}
