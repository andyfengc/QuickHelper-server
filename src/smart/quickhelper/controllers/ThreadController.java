package smart.quickhelper.controllers;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import smart.quickhelper.entities.User;
import smart.quickhelper.services.IMessageService;
import smart.quickhelper.services.ITaskService;
import smart.quickhelper.services.IUserService;
import smart.quickhelper.services.MessageServiceBase;
import smart.quickhelper.services.TaskServiceBase;
import smart.quickhelper.services.UserServiceBase;

@Path("/api")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ThreadController {
	private IMessageService messageService = new MessageServiceBase();
	private IUserService userService = new UserServiceBase();
	private ITaskService taskService = new TaskServiceBase();

	@Path("/users/{userId}/threads")
	@GET
	public Response getThreadsByUser(@PathParam("userId") long userId) {
		User user = this.userService.GetUserById(userId);
		if (user == null) {
			return Response.status(Status.NOT_FOUND).entity(new Object() {
				public String message = "cannot find user by id - " + userId;
			}).type(MediaType.APPLICATION_JSON).build();
		}
		Collection<smart.quickhelper.entities.Thread> threads = this.messageService.GetThreadsByUser(userId);
		return Response.status(Status.OK)
				.entity(threads)
				.build();
	}
	
	@GET
	@Path("/users/{userId}/threads/participated")
	public Response getParticipatedThreadsByUser(@PathParam("userId") long userId) {
		User user = this.userService.GetUserById(userId);
		if (user == null) {
			return Response.status(Status.NOT_FOUND).entity(new Object() {
				public String message = "cannot find user by id - " + userId;
			}).type(MediaType.APPLICATION_JSON).build();
		}
		Collection<smart.quickhelper.entities.Thread> threads = this.messageService.GetParticipatedThreadsByUser(userId);
		return Response.status(Status.OK)
				.entity(threads)
				.build();
	}
	
	@GET
	@Path("/threads/{threadId}")
	public Response getThreadDetails(@PathParam("threadId") long threadId) {
		smart.quickhelper.entities.Thread thread = this.messageService.GetThreadById(threadId);
		if (thread == null) {
			return Response.status(Status.NOT_FOUND)
					.entity(new Object() { public String message = "cannot find thread by id - " + threadId;})
					.build();
		}
		return Response.status(Status.OK)
				.entity(thread)
				.build();
	}
}
