package smart.quickhelper.services;

import java.util.List;

import smart.quickhelper.entities.Message;
import smart.quickhelper.entities.Task;
import smart.quickhelper.entities.Thread;
import smart.quickhelper.entities.User;

public interface IMessageService {
	public void SendMessage(Task task, Thread thread, Message message);

	public List<Message> GetReceivedMessages(long userId);

	public List<Message> GetSentMessages(long userId);

	public List<Thread> GetThreadsByTask(long taskId);

	public List<Message> GetMessagesByThread(long threadId);
}
