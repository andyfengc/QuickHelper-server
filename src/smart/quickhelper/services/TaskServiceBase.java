package smart.quickhelper.services;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import smart.quickhelper.entities.Task;

public class TaskServiceBase implements ITaskService {

	@Override
	public List<Task> GetAllTasks() {
		try (SessionFactory sf = new Configuration().configure().buildSessionFactory()) {
			try (Session session = sf.openSession()) {
				List<Task> result = session.createCriteria(Task.class).list();
				return result;
			}
		}
	}

	@Override
	public List<Task> GetTasksByAuthor(long authorId) {
		try (SessionFactory sf = new Configuration().configure().buildSessionFactory()) {
			try (Session session = sf.openSession()) {
				Query query = session.createQuery("from Task t where t.AuthorId = ? ");
				query.setParameter(0, authorId);
				List<Task> result = query.getResultList();
				return result;
			}
		}
	}

	@Override
	public void AddTask(Task task) {
		try (SessionFactory sf = new Configuration().configure().buildSessionFactory()) {
			try (Session session = sf.openSession()) {
				session.beginTransaction();
				session.save(task);
				session.getTransaction().commit();
			}
		}
	}

	@Override
	public void DeleteTask(long taskId) {
		Task task = GetTaskById(taskId);
		if (task != null) {
			DeleteTask(task);
		}		
	}

	@Override
	public void DeleteTask(Task task) {
		try (SessionFactory sf = new Configuration().configure().buildSessionFactory()) {
			try (Session session = sf.openSession()) {
				session.beginTransaction();
				session.delete(task);
				session.getTransaction().commit();
			}
		}
	}

	@Override
	public void UpdateTask(Task task) {
		try (SessionFactory sf = new Configuration().configure().buildSessionFactory()) {
			try (Session session = sf.openSession()) {
				session.beginTransaction();
				session.update(task);
				session.getTransaction().commit();
			}
		}
	}

	@Override
	public List<Task> SearchTasks(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Task GetTaskById(long taskId) {
		try (SessionFactory sf = new Configuration().configure().buildSessionFactory()) {
			try (Session session = sf.openSession()) {
				Task task = session.find(Task.class, taskId);
				return task;
			}
		}
	}

}
