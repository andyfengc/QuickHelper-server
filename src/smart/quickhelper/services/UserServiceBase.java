package smart.quickhelper.services;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import smart.quickhelper.entities.Task;
import smart.quickhelper.entities.User;

public class UserServiceBase extends ServiceBase implements IUserService {

	@Override
	public void AddUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User GetUserById(long userId) {
		try (Session session = getSessionFactory().openSession()) {
			User user = session.find(User.class, userId);
			return user;
		}
	}

	@Override
	public Collection<User> GetAllUsers() {
		try (Session session = getSessionFactory().openSession()){
			Collection<User> users= session.createCriteria(User.class).list();
			return users;
		}
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
