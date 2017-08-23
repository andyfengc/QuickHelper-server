package smart.quickhelper.services;

import java.util.Collection;
import java.util.List;

import smart.quickhelper.entities.Task;

public interface ITaskService {
	public Collection<Task> GetAllTasks();

	public Collection<Task> GetTasksByAuthor(long authorId);

	public void AddTask(Task task);

	public void DeleteTask(long taskId);
	public void DeleteTask(Task task);

	public void UpdateTask(Task task);

	public Task GetTaskById(long taskId);

	/**
	 * search tasks by title, content
	 * 
	 * @param q
	 * @return
	 */
	public Collection<Task> SearchTasks(String q);
}
