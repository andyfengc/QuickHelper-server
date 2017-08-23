package smart.quickhelper.services;

import java.util.Collection;
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

	public Collection<Message> GetMessagesByUserAndThread(long threadId);

	public Collection<Message> GetAllMessagesByUser(long userId);

	public Collection<Message> GetReceivedMessagesByUser(long userId);

	public Message GetFirstMessageByThread(long threadId);

	public Collection<Message> GetSentMessagesByUser(long userId);

	public Collection<Thread> GetThreadsByTask(long taskId);

	public Collection<Message> GetMessagesByTask(long taskId);

	public Collection<Message> GetMessagesByThread(long threadId);

	public Collection<Thread> GetParticipatedThreadsByUser(long userId);
	
	public Thread GetThreadById(long threadId);

	public Collection<Message> GetParticipatedFirstMessagesByUser(long userId);

	public Collection<Message> GetFirstMessagesInitiatedByUser(long userId);

	public Collection<Thread> GetThreadsByUser(long userId);

	public Collection<Message> GetFirstMessagesByUser(long userId);

	public Message GetMessageById(long messageId);

	Message GetMessageByMessageLine(long messageLineId);

	public void MarkRead(long messageLineId);

}
