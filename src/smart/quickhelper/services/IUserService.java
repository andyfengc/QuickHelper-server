package smart.quickhelper.services;

import java.util.List;

import smart.quickhelper.entities.User;

public interface IUserService {
	public void AddUser(User user);
	public User GetUserById(long userId);
	public List<User> GetAllUsers();
	public void DeleteUser(long userId);
	public void DeleteUser(User user);
	public void UpdateUser(User user);
}
