package smart.quickhelper.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	// private long SenderId;
	// private long ReceiverId;
	// private long TaskId;
	// private long ParentMessageId;
	private String Text;
	private Date CreatedTime;

	@ManyToOne
	public User Sender;
	@ManyToOne
	public User Receiver;
	@ManyToOne
	public Message ParentMessage;
	@ManyToOne
	public Task Task;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "User")
	@Transient
	private Set<Message> Messages = new HashSet<>();

	public Set<Message> getMessages() {
		return Messages;
	}

	public void setMessages(Set<Message> messages) {
		Messages = messages;
	}
}
