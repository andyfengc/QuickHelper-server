package smart.quickhelper.controllers;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import smart.quickhelper.entities.Category;
import smart.quickhelper.entities.Task;
import smart.quickhelper.entities.User;
import smart.quickhelper.models.ApiResult;
import smart.quickhelper.services.CategoryServiceBase;
import smart.quickhelper.services.ICategoryService;
import smart.quickhelper.services.ITaskService;
import smart.quickhelper.services.IUserService;
import smart.quickhelper.services.TaskServiceBase;
import smart.quickhelper.services.UserServiceBase;
import smart.quickhelper.utils.CustomJsonDateDeserializer;
import smart.quickhelper.utils.CustomJsonDateSerializer;

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
	public Response GetTask(@PathParam("id")long id) {
		Task selectedTask = this.taskService.GetTaskById(id);
		if (selectedTask == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity(new Object() {public String message = "cannot find task by id - " + id;})
					.type(MediaType.APPLICATION_JSON)
					.build();
		}
		return Response.ok(selectedTask, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/users/{id}/tasks")
	public List<Task> GetTasksByUser(@PathParam("id") long id){
		List<Task> tasks = this.taskService.GetTasksByAuthor(id);
		return tasks;
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + "; charset=utf-8")
	@Path("/tasks/{id}")
	public Response UpdateTask(@PathParam("id") long id, Task task) {
		//
		Task selectedTask = this.taskService.GetTaskById(id);
		if (selectedTask == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity(new Object() {public String message = "cannot find task by id - " + id;})
					.type(MediaType.APPLICATION_JSON)
					.build();
		}
		//
		User author = this.userService.GetUserById(task.getAuthorId());
		selectedTask.setAuthor(author);
		//
		Category category = this.categoryService.GetCategoryById(task.getCategoryId());
		selectedTask.setCategory(category);
		//
		selectedTask.setTitle(task.getTitle());
		selectedTask.setAddress(task.getAddress());
		selectedTask.setContent(task.getContent());
		selectedTask.setStartTime(task.getStartTime());
		selectedTask.setEndTime(task.getEndTime());
		selectedTask.setPrice(task.getPrice());
		this.taskService.UpdateTask(selectedTask);
		//
		ApiResult result = new ApiResult();
		result.setSuccess(true);
		return Response.ok(result).build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + "; charset=utf-8")
	@Path("/tasks/{id}")
	public Response DeleteTask(@PathParam("id") long id){
		//
		Task selectedTask = this.taskService.GetTaskById(id);
		if (selectedTask == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity(new Object() {public String message = "cannot find task by id - " + id;})
					.type(MediaType.APPLICATION_JSON)
					.build();
		}
		this.taskService.DeleteTask(selectedTask);
		//
		ApiResult result = new ApiResult();
		result.setSuccess(true);
		return Response.ok(result).build();
	}
}
