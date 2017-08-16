package smart.quickhelper.entities;

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
@Table(name = "ThreadLine")
public class ThreadLine {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	
	private long ParticipantId;
	
	private long ThreadId;
	
	public long getId()	{
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}

	public long getParticipantId() {
		return ParticipantId;
	}
	public void setParticipantId(long participantId) {
		ParticipantId = participantId;
	}
	public long getThreadId() {
		return ThreadId;
	}
	public void setThreadId(long threadId) {
		ThreadId = threadId;
	}

	@ManyToOne
	@JoinColumn(name="ThreadId", insertable = false, updatable = false)
	private Thread Thread;
	
	public Thread getThread() {
		return Thread;
	}
	public void setThread(Thread thread) {
		Thread = thread;
	}

	@ManyToOne
	@JoinColumn(name="ParticipantId", insertable = false, updatable = false)
	private User Participant;
	
	public User getParticipant() {
		return Participant;
	}
	public void setParticipant(User participant) {
		Participant = participant;
	}
}
