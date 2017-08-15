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
	private long AuthorId;
	private long CategoryId;
	private String Title;
	private String Content;
	private Date CreatedTime;
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

	public long getAuthorId() {
		return AuthorId;
	}

	public void setAuthorId(long authorId) {
		AuthorId = authorId;
	}
	public long getCategoryId() {
		return CategoryId;
	}

	public void setCategoryId(long categoryId) {
		CategoryId = categoryId;
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
	private User Author;

	public User getAuthor() {
		return Author;
	}

	public void setAuthor(User author) {
		Author = author;
		this.setAuthorId(author.getId());
	}
	
	@ManyToOne
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


	

}
