package smart.quickhelper.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Category")
public class Category {
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	private String Name;
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Set<Task> getTasks() {
		return Tasks;
	}
	public void setTasks(Set<Task> tasks) {
		Tasks = tasks;
	}
	@OneToMany(cascade=CascadeType.ALL, mappedBy ="Category")
	@Transient
	private Set<Task> Tasks = new HashSet<Task>();
}
