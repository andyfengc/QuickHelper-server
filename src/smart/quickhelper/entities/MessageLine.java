package smart.quickhelper.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MessageLine")
public class MessageLine {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	
	private boolean IsRead;
	
	private Date ReadTime;
	
	private boolean IsStared;
	
	private Date StaredTime;
	
	private boolean IsDeleted;
	
	private Date DeletedTime;
	
	private long RecipientId;
	
	private long MessageId;
	
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public boolean isIsRead() {
		return IsRead;
	}

	public void setIsRead(boolean isRead) {
		IsRead = isRead;
	}

	public Date getReadTime() {
		return ReadTime;
	}

	public void setReadTime(Date readTime) {
		ReadTime = readTime;
	}

	public boolean isIsStared() {
		return IsStared;
	}

	public void setIsStared(boolean isStared) {
		IsStared = isStared;
	}

	public Date getStaredTime() {
		return StaredTime;
	}

	public void setStartedTime(Date staredTime) {
		StaredTime = staredTime;
	}

	public boolean isIsDeleted() {
		return IsDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		IsDeleted = isDeleted;
	}

	public Date getDeletedTime() {
		return DeletedTime;
	}

	public void setDeletedTime(Date deletedTime) {
		DeletedTime = deletedTime;
	}

	public long getRecipientId() {
		return RecipientId;
	}

	public void setRecipientId(long recipientId) {
		RecipientId = recipientId;
	}

	public long getMessageId() {
		return MessageId;
	}

	public void setMessageId(long messageId) {
		MessageId = messageId;
	}

	@ManyToOne
	@JoinColumn(name="MessageId", insertable = false, updatable = false)
	private Message Message;
	
	@ManyToOne
	@JoinColumn(name="RecipientId", insertable = false, updatable = false)
	private User Recipient;
	
	public User getRecipient() {
		return Recipient;
	}

	public void setRecipient(User recipient) {
		Recipient = recipient;
	}

	public Message getMessage() {
		return Message;
	}

	public void setMessage(Message message) {
		Message = message;
	}
	
}
