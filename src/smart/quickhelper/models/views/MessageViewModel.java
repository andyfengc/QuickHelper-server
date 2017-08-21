package smart.quickhelper.models.views;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import smart.quickhelper.entities.Message;
import smart.quickhelper.entities.Task;
import smart.quickhelper.entities.User;

public class MessageViewModel {
	private Task task;
	private Message message;
	private List<User> recipients;

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public List<User> getRecipients() {
		return recipients;
	}

	@JsonProperty("recipients")
	public void setRecipients(List<User> recipients) {
		this.recipients = recipients;
	}

}
