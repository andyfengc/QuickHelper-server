package smart.quickhelper.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	private String Avatar;
	private Date CreatedTime;
	private String UserName;
	private String NickName;
	private String Password;
	private String Email;
	private String PhoneNumber;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getAvatar() {
		return Avatar;
	}

	public void setAvatar(String avatar) {
		Avatar = avatar;
	}

	public Date getCreatedTime() {
		return CreatedTime;
	}

	public void setCreatedTime(Date createdTime) {
		CreatedTime = createdTime;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}

	@JsonIgnore
	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "User")
	@Transient
	private Set<Task> Tasks = new HashSet<Task>();

	public Set<Task> getTasks() {
		return Tasks;
	}

	public void setTasks(Set<Task> tasks) {
		Tasks = tasks;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "User")
	@Transient
	private Set<Message> SentMessages = new HashSet<>();

	public Set<Message> getSentMessages() {
		return SentMessages;
	}

	public void setSentMessages(Set<Message> sentMessages) {
		SentMessages = sentMessages;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "User")
	@Transient
	private Set<MessageLine> ReceivedMessages = new HashSet<>();

	public Set<MessageLine> getReceivedMessages() {
		return ReceivedMessages;
	}

	public void setReceivedMessages(Set<MessageLine> receivedMessages) {
		ReceivedMessages = receivedMessages;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "User")
	@Transient
	private Set<ThreadLine> ThreadLines = new HashSet<>();

	public Set<ThreadLine> getThreadLines() {
		return ThreadLines;
	}

	public void setThreadLines(Set<ThreadLine> threadLines) {
		ThreadLines = threadLines;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CreatedTime == null) ? 0 : CreatedTime.hashCode());
		result = prime * result + ((Email == null) ? 0 : Email.hashCode());
		result = prime * result + ((NickName == null) ? 0 : NickName.hashCode());
		result = prime * result + ((PhoneNumber == null) ? 0 : PhoneNumber.hashCode());
		result = prime * result + ((UserName == null) ? 0 : UserName.hashCode());
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
		User other = (User) obj;
		if (CreatedTime == null) {
			if (other.CreatedTime != null)
				return false;
		} else if (!CreatedTime.equals(other.CreatedTime))
			return false;
		if (Email == null) {
			if (other.Email != null)
				return false;
		} else if (!Email.equals(other.Email))
			return false;
		if (NickName == null) {
			if (other.NickName != null)
				return false;
		} else if (!NickName.equals(other.NickName))
			return false;
		if (PhoneNumber == null) {
			if (other.PhoneNumber != null)
				return false;
		} else if (!PhoneNumber.equals(other.PhoneNumber))
			return false;
		if (UserName == null) {
			if (other.UserName != null)
				return false;
		} else if (!UserName.equals(other.UserName))
			return false;
		return true;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "UserRole", joinColumns = @JoinColumn(name = "UserId"), inverseJoinColumns = @JoinColumn(name = "RoleId"))
	private Set<Role> Roles;

	public Set<Role> getRoles() {
		return Roles;
	}

	public void setRoles(Set<Role> roles) {
		Roles = roles;
	}

}
