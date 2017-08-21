package smart.quickhelper.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "MessageLine")
public class MessageLine {
	
	public MessageLine() {
		this.IsDeleted = false;
		this.IsRead = false;
		this.IsStared = false;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	
	private boolean IsRead;
	
	private Date ReadTime;
	
	private boolean IsStared;
	
	private Date StaredTime;
	
	private boolean IsDeleted;
	
	private Date DeletedTime;
	
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

	@ManyToOne
	@JoinColumn(name="MessageId")
	private Message Message;
	
	@ManyToOne
	@JoinColumn(name="RecipientId")
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
