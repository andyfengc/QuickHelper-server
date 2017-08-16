package smart.quickhelper.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Thread")
public class Thread {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "Thread")
	@Transient
	private Set<ThreadLine> ThreadLines =new HashSet<>();
	
	public Set<ThreadLine> getThreadLines() {
		return ThreadLines;
	}

	public void setThreadLines(Set<ThreadLine> threadLines) {
		ThreadLines = threadLines;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "Thread")
	@Transient
	private Set<Message> Messages =new HashSet<>();
	
	public Set<Message> getMessages() {
		return Messages;
	}

	public void setMessages(Set<Message> messages) {
		Messages = messages;
	}
	
}
