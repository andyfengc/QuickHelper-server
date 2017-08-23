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
	private String Text;
	private Date CreatedTime;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
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
	@JoinColumn(name="SenderId")
	private User Sender;
	
	@ManyToOne
	@JoinColumn(name="TaskId")
	private Task Task;
	
	@ManyToOne
	@JoinColumn(name="ParentId")
	private Message Parent;
	
	@ManyToOne
	@JoinColumn(name="ThreadId")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CreatedTime == null) ? 0 : CreatedTime.hashCode());
		result = prime * result + ((Parent == null) ? 0 : Parent.hashCode());
		result = prime * result + ((Sender == null) ? 0 : Sender.hashCode());
		result = prime * result + ((Text == null) ? 0 : Text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (CreatedTime == null) {
			if (other.CreatedTime != null)
				return false;
		} else if (!CreatedTime.equals(other.CreatedTime))
			return false;
		if (Parent == null) {
			if (other.Parent != null)
				return false;
		} else if (!Parent.equals(other.Parent))
			return false;
		if (Sender == null) {
			if (other.Sender != null)
				return false;
		} else if (!Sender.equals(other.Sender))
			return false;
		if (Text == null) {
			if (other.Text != null)
				return false;
		} else if (!Text.equals(other.Text))
			return false;
		return true;
	}

}
