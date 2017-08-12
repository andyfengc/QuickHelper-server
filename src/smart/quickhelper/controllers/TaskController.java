package smart.quickhelper.controllers;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import smart.quickhelper.CustomJsonDateDeserializer;
import smart.quickhelper.entities.Category;
import smart.quickhelper.entities.Task;
import smart.quickhelper.entities.User;
import smart.quickhelper.services.CategoryServiceBase;
import smart.quickhelper.services.ICategoryService;
import smart.quickhelper.services.ITaskService;
import smart.quickhelper.services.IUserService;
import smart.quickhelper.services.TaskServiceBase;
import smart.quickhelper.services.UserServiceBase;

@Path("/api")
public class TaskController {
	private ITaskService taskService = new TaskServiceBase();
	private IUserService userService = new UserServiceBase();
	private ICategoryService categoryService = new CategoryServiceBase();

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/tasks")
	public List<Task> GetAllTasks() {
		return taskService.GetAllTasks();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/tasks")
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public Object AddTask(Task task) {
		//
		User author = this.userService.GetUserById(task.getAuthorId());
		task.setAuthor(author);
		//
		Category category = this.categoryService.GetCategoryById(task.getCategoryId());
		task.setCategory(category);
		
		this.taskService.AddTask(task);
		return new Object(){public boolean success = true;};
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
	@Path("/tasks/{id}")
	public Task GetTask(@PathParam("id")long id) {
		Task task = this.taskService.GetTaskById(id);
		return task;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/users/{id}/tasks")
	public List<Task> GetTasksByUser(@PathParam("id") long id){
		List<Task> tasks = this.taskService.GetTasksByAuthor(id);
		return tasks;
	}
	
}
