package smart.quickhelper.tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import smart.quickhelper.entities.Task;
import smart.quickhelper.entities.User;

public class DatabaseTest {

	@Test
	public void SaveUser_ShouldSucceed() {
		User user =new User();
		user.setUserName("Andy Feng");
		user.setNickName("andy");
		user.setEmail("andyinbox3@gmail.com");
		//
		SessionFactory sf =new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Object x = session.save(user);
		session.getTransaction().commit();
		session.close();
		sf.close();
		System.out.println(x);
	}

	@Test
	public void SaveTask_ShouldSucceed() {
		Task task = new Task();
		task.setAddress("123 steven ave");
		task.setTitle("找一位月嫂");
		task.setCreatedTime(new Date());
		//
		SessionFactory sf =new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		// find a user
		User selectedUser = session.find(User.class, 1L);
		task.setAuthor(selectedUser);
		// find a task
		//Task selectedTask = session.find(Task.class, 7L); 
		
		Object x = session.save(task);
		session.getTransaction().commit();
		session.close();
		sf.close();
		
		
		System.out.println(x);
	}
}
