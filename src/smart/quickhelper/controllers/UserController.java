package smart.quickhelper.controllers;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import smart.quickhelper.entities.Task;
import smart.quickhelper.entities.User;
import smart.quickhelper.services.CategoryServiceBase;
import smart.quickhelper.services.ICategoryService;
import smart.quickhelper.services.ITaskService;
import smart.quickhelper.services.IUserService;
import smart.quickhelper.services.TaskServiceBase;
import smart.quickhelper.services.UserServiceBase;

@Path("/api")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class UserController {
	private IUserService userService = new UserServiceBase();

	@GET
	@Path("/users")
	public Collection<User> GetAllUsers() {
		return this.userService.GetAllUsers();
	}

}
