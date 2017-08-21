package smart.quickhelper.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import smart.quickhelper.entities.Category;
import smart.quickhelper.entities.Message;
import smart.quickhelper.entities.Task;
import smart.quickhelper.entities.User;
import smart.quickhelper.exceptions.MessageException;
import smart.quickhelper.models.views.MessageViewModel;
import smart.quickhelper.services.IMessageService;
import smart.quickhelper.services.ITaskService;
import smart.quickhelper.services.IUserService;
import smart.quickhelper.services.MessageServiceBase;
import smart.quickhelper.services.TaskServiceBase;
import smart.quickhelper.services.UserServiceBase;

@Path("/api")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class MessageController {
	private IMessageService messageService = new MessageServiceBase();
	private IUserService userService = new UserServiceBase();
	private ITaskService taskService = new TaskServiceBase();

	@GET
	@Path("/users/{userId}/messages")
	public Response GetInboxMessagesByUser(@PathParam("userId") long userId) {
		//
		User user = this.userService.GetUserById(userId);
		if (user == null) {
			return Response.status(Response.Status.NOT_FOUND).entity(new Object() {
				public String message = "cannot find user by id - " + userId;
			}).type(MediaType.APPLICATION_JSON).build();
		}
		//
		List<Message> messages = this.messageService.GetReceivedMessagesByUser(userId).stream()
				.sorted((i1, i2) -> i1.getCreatedTime().compareTo(i2.getCreatedTime())).collect(Collectors.toList());
		return Response.status(Response.Status.OK).entity(messages).type(MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/messages/{messageId}")
	public Response GetMessageById(@PathParam("messageId") long messageId) {
		Message message = this.messageService.GetMessageById(messageId);
		if (message == null) {
			return Response.status(Status.NOT_FOUND).entity(new Object() {
				public String message = "cannot find message by id - " + messageId;
			}).type(MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Status.OK)
				.entity(message)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

	@POST
	@Path("/users/{userId}/messages")
	public Response SentMessage(@PathParam("userId") long userId, MessageViewModel messageVm) {
		// get sender
		User sender = this.userService.GetUserById(userId);
		if (sender == null) {
			return Response.status(Response.Status.NOT_FOUND).entity(new Object() {
				public String message = "cannot find user by id - " + userId;
			}).type(MediaType.APPLICATION_JSON).build();
		}
		// get task
		Task task = null;
		if (messageVm.getTask() != null) {
			task = this.taskService.GetTaskById(messageVm.getTask().getId());
		}
		// get message
		Message message = messageVm.getMessage();
		message.setCreatedTime(new Date());
		// get recipient
		Set<User> recipients = new HashSet<>();
		for (User recipient : messageVm.getRecipients()) {
			long recipientId = recipient.getId();
			User selectedRecipient = this.userService.GetUserById(recipientId);
			if (selectedRecipient != null) {
				recipients.add(selectedRecipient);
			}
		}
		// sent message
		this.messageService.SendMessage(task, sender, new ArrayList<>(recipients), message);
		return Response.ok().build();
	}

	@GET
	@Path("/messges/{messageId}")
	public Response GetMesssageById(@PathParam("messageId") long messageId) {
		Message message = this.messageService.GetMessageById(messageId);
		if (message == null) {
			return Response.status(Status.NOT_FOUND).entity(new Object() {
				public String message = "cannot find message by id - " + messageId;
			}).type(MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Status.OK).entity(message).type(MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Path("/users/{userId}/messages/{messageId}")
	public Response ReplyMessage(@PathParam("userId") long userId, @PathParam("messageId") long messageId,
			MessageViewModel messageVm) {
		// get sender
		User sender = this.userService.GetUserById(userId);
		if (sender == null) {
			return Response.status(Response.Status.NOT_FOUND).entity(new Object() {
				public String message = "cannot find user by id - " + userId;
			}).type(MediaType.APPLICATION_JSON).build();
		}
		// get parent message
		Message parent = this.messageService.GetMessageById(messageId);
		if (parent == null) {
			return Response.status(Status.NOT_FOUND).entity(new Object() {
				public String message = "cannot find message by id - " + messageId;
			}).type(MediaType.APPLICATION_JSON).build();
		}
		// get message
		Message message = messageVm.getMessage();
		message.setCreatedTime(new Date());
		// sent message
		try {
			this.messageService.ReplyMessage(sender, parent, message);
		} catch (MessageException e) {
			return Response.status(Status.BAD_REQUEST).entity(new Object() {
				public String message = "failed to reply message - " + messageId + ": " + e.getMessage();
			}).type(MediaType.APPLICATION_JSON).build();
		}
		return Response.ok().build();
	}
}
