package smart.quickhelper.controllers;

import java.util.Collection;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

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
	private IUserService userService;
	
	public UserController() {
	}

	@GET
	@Path("/users")
	public Collection<User> GetAllUsers(@Context ServletContext servletContext) {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		this.userService = applicationContext.getBean("userService", IUserService.class);
		return this.userService.GetAllUsers();
	}
}
