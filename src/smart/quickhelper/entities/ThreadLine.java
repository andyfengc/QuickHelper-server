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
	

	public long getId()	{
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}

	@ManyToOne
	@JoinColumn(name="ThreadId")
	private Thread Thread;
	
	public Thread getThread() {
		return Thread;
	}
	public void setThread(Thread thread) {
		Thread = thread;
	}

	@ManyToOne
	@JoinColumn(name="ParticipantId")
	private User Participant;
	
	public User getParticipant() {
		return Participant;
	}
	public void setParticipant(User participant) {
		Participant = participant;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Participant == null) ? 0 : Participant.hashCode());
		result = prime * result + ((Thread == null) ? 0 : Thread.hashCode());
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
		ThreadLine other = (ThreadLine) obj;
		if (Participant == null) {
			if (other.Participant != null)
				return false;
		} else if (!Participant.equals(other.Participant))
			return false;
		if (Thread == null) {
			if (other.Thread != null)
				return false;
		} else if (!Thread.equals(other.Thread))
			return false;
		return true;
	}
	
}
