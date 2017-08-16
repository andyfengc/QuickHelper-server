package smart.quickhelper.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Message")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	private long SenderId;
	private long TaskId;
	private long ThreadId;
	private String Text;
	private Date CreatedTime;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public long getSenderId() {
		return SenderId;
	}

	public void setSenderId(long senderId) {
		SenderId = senderId;
	}

	public long getTaskId() {
		return TaskId;
	}

	public void setTaskId(long taskId) {
		TaskId = taskId;
	}

	public long getThreadId() {
		return ThreadId;
	}

	public void setThreadId(long threadId) {
		ThreadId = threadId;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}

	public Date getCreatedTime() {
		return CreatedTime;
	}

	public void setCreatedTime(Date createdTime) {
		CreatedTime = createdTime;
	}

	public User getSender() {
		return Sender;
	}

	public void setSender(User sender) {
		Sender = sender;
	}

	public Task getTask() {
		return Task;
	}

	public void setTask(Task task) {
		Task = task;
	}

	public Message getParent() {
		return Parent;
	}

	public void setParent(Message parent) {
		Parent = parent;
	}

	public Thread getThread() {
		return Thread;
	}

	public void setThread(Thread thread) {
		Thread = thread;
	}
	
	@ManyToOne
	@JoinColumn(name="SenderId", insertable = false, updatable = false)
	private User Sender;
	
	@ManyToOne
	@JoinColumn(name="TaskId", insertable = false, updatable = false)
	private Task Task;
	
	@ManyToOne
	@JoinColumn(name="ParentId", insertable=false, updatable=false)
	private Message Parent;
	
	@ManyToOne
	@JoinColumn(name="ThreadId", insertable = false, updatable = false)
	private Thread Thread;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "Message")
	@Transient
	private Set<MessageLine> MessageLines = new HashSet<>();
	public Set<MessageLine> getMessageLines() {
		return MessageLines;
	}

	public void setMessageLines(Set<MessageLine> messageLines) {
		MessageLines = messageLines;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "Message")
	@Transient
	private Set<Message> ChildMessages = new HashSet<>();

	public Set<Message> getChildMessages() {
		return ChildMessages;
	}

	public void setChildMessages(Set<Message> childMessages) {
		ChildMessages = childMessages;
	}
	

}
