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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import smart.quickhelper.utils.CustomJsonDateDeserializer;
import smart.quickhelper.utils.CustomJsonDateSerializer;

@Entity
@Table(name = "Task")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;

	private String Title;
	private Date CreatedTime;
	private String Content;
	private Date StartTime;
	private Date EndTime;
	private double Price;
	private String Address;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
	@JsonSerialize(using=CustomJsonDateSerializer.class)
	public Date getCreatedTime() {
		return CreatedTime;
	}

	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setCreatedTime(Date createdTime) {
		CreatedTime = createdTime;
	}

	@JsonSerialize(using=CustomJsonDateSerializer.class)
	public Date getStartTime() {
		return StartTime;
	}

	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setStartTime(Date startTime) {
		StartTime = startTime;
	}

	@JsonSerialize(using=CustomJsonDateSerializer.class)
	public Date getEndTime() {
		return EndTime;
	}

	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setEndTime(Date endTime) {
		EndTime = endTime;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	@ManyToOne
	@JoinColumn(name="AuthorId")
	private User Author;

	public User getAuthor() {
		return Author;
	}
	
	public void setAuthor(User author) {
		Author = author;
	}
	
	@ManyToOne
	@JoinColumn(name="CategoryId")
	private Category Category;

	public Category getCategory() {
		return Category;
	}

	public void setCategory(Category category) {
		Category = category;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "User")
	@Transient
	private Set<Message> Messages = new HashSet<>();
	
	public Set<Message> getMessages() {
		return Messages;
	}

	public void setMessages(Set<Message> messages) {
		Messages = messages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Address == null) ? 0 : Address.hashCode());
		result = prime * result + ((Author == null) ? 0 : Author.hashCode());
		result = prime * result + ((CreatedTime == null) ? 0 : CreatedTime.hashCode());
		result = prime * result + ((EndTime == null) ? 0 : EndTime.hashCode());
		long temp;
		temp = Double.doubleToLongBits(Price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((StartTime == null) ? 0 : StartTime.hashCode());
		result = prime * result + ((Title == null) ? 0 : Title.hashCode());
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
		Task other = (Task) obj;
		if (Address == null) {
			if (other.Address != null)
				return false;
		} else if (!Address.equals(other.Address))
			return false;
		if (Author == null) {
			if (other.Author != null)
				return false;
		} else if (!Author.equals(other.Author))
			return false;
		if (CreatedTime == null) {
			if (other.CreatedTime != null)
				return false;
		} else if (!CreatedTime.equals(other.CreatedTime))
			return false;
		if (EndTime == null) {
			if (other.EndTime != null)
				return false;
		} else if (!EndTime.equals(other.EndTime))
			return false;
		if (Double.doubleToLongBits(Price) != Double.doubleToLongBits(other.Price))
			return false;
		if (StartTime == null) {
			if (other.StartTime != null)
				return false;
		} else if (!StartTime.equals(other.StartTime))
			return false;
		if (Title == null) {
			if (other.Title != null)
				return false;
		} else if (!Title.equals(other.Title))
			return false;
		return true;
	}

}
