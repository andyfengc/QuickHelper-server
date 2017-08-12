package smart.quickhelper.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import smart.quickhelper.entities.Task;
import smart.quickhelper.entities.User;

public class UserServiceBase implements IUserService{

	@Override
	public void AddUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User GetUserById(long userId) {
		try (SessionFactory sf = new Configuration().configure().buildSessionFactory()) {
			try (Session session = sf.openSession()) {
				User user = session.find(User.class, userId);
				return user;
			}
		}
	}

	@Override
	public List<User> GetAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void DeleteUser(long userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DeleteUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UpdateUser(User user) {
		// TODO Auto-generated method stub
		
	}

}
