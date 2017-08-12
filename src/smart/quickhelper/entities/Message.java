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

	@ManyToOne(cascade = CascadeType.PERSIST)
	public User Sender;
	@ManyToOne(cascade = CascadeType.PERSIST)
	public User Receiver;
	@ManyToOne(cascade = CascadeType.PERSIST)
	public Message ParentMessage;
}
