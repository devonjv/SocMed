package model;

import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Component;

import utilities.Helper;

@Component
@Entity
@Table(name = "SOCMED_MESSAGE")
public class Message {

	private final static Logger ibis = Logger.getLogger(User.class);

	@Id
	@Column(name = "message_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "message_sent")
	private Date sent;

	@Column(name = "message_text")
	private String text;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "sender_username")
	private User sender;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "receiver_username")
	private User receiver;

	/**
	 * Determines whether the message is visible to the receiver or not
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "message_status")
	private MessageStatus status;

	public Message() {
	}

	public Message(String text, User sender, User receiver) {
		/**
		 * Used to create a new message
		 */
		super();
		this.text = text;
		this.sender = sender;
		this.receiver = receiver;

		if (!(sender.banned(receiver))) {
			status = Helper.statusService().getVisibleMessage();
		} else {
			status = Helper.statusService().getHiddenMessage();
		}
		Helper.messageDAO().insertMessage(this);
		ibis.info("Message sent from " + sender.name() + " to " + receiver.name());
	}

	public Message(int id, Date sent, String text, User sender, User receiver, MessageStatus status) {
		super();
		this.id = id;
		this.sent = sent;
		this.text = text;
		this.sender = sender;
		this.receiver = receiver;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public Date getSent() {
		return sent;
	}

	public String getText() {
		return text;
	}

	public User getSender() {
		return sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public boolean isVisible() {
		return status.equals(Helper.statusService().getVisibleMessage());
	}

	public void hide() {
		status = Helper.statusService().getHiddenMessage();
		ibis.info("Message #" + id + " hidden");
	}
}
