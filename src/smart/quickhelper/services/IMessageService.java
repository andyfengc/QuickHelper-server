package smart.quickhelper.services;

import java.util.List;

import javax.transaction.NotSupportedException;

import smart.quickhelper.entities.Message;
import smart.quickhelper.entities.MessageLine;
import smart.quickhelper.entities.Task;
import smart.quickhelper.entities.Thread;
import smart.quickhelper.entities.User;
import smart.quickhelper.exceptions.MessageException;

public interface IMessageService {
	public void SendMessage(User sender, List<User> recipients, Message message);

	public void SendMessage(Task task, User sender, List<User> recipients, Message message);

	public void ReplyMessage(User sender, Message parent, Message message) throws MessageException;

	public void ReplyMessage(User sender, MessageLine parentMessageLine, Message message) throws MessageException;
	
	public void ForwardMessage(User sender, List<User> recipients, Message parent, Message message) throws MessageException;

	public List<Message> GetMessagesByUserAndThread(long userId, long threadId);

	public List<Message> GetAllMessagesByUser(long userId);

	public List<Message> GetReceivedMessagesByUser(long userId);

	public Message GetFirstMessageByThread(long threadId);

	public List<Message> GetSentMessagesByUser(long userId);

	public List<Thread> GetThreadsByTask(long taskId);

	public List<Message> GetMessagesByTask(long taskId);

	public List<Message> GetMessagesByThread(long threadId);

	public List<Thread> GetParticipatedThreadsByUser(long userId);

	public List<Message> GetParticipatedFirstMessagesByUser(long userId);

	public List<Message> GetFirstMessagesInitiatedByUser(long userId);

	public List<Thread> GetThreadsByUser(long userId);

	public List<Message> GetFirstMessagesByUser(long userId);

	public Message GetMessageById(long messageId);

	Message GetMessageByMessageLine(long messageLineId);

	public void MarkRead(long messageLineId);

}
