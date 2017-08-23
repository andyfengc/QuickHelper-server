package smart.quickhelper.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne
	@JoinColumn(name="ParentId", insertable=false, updatable=false)
	private Category Parent;

	public Category getParent() {
		return Parent;
	}

	public void setParent(Category parent) {
		Parent = parent;
	}

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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "Category")
	@Transient
	private Set<Task> Tasks = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "Category")
	@Transient
	private Set<Category> ChildCategories = new HashSet<>();

	public Set<Category> getChildCategories() {
		return ChildCategories;
	}

	public void setChildCategories(Set<Category> childCategories) {
		ChildCategories = childCategories;
	}	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
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
		Category other = (Category) obj;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		return true;
	}
	
}
