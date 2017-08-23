package smart.quickhelper.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.NotSupportedException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mysql.cj.core.Messages;

import smart.quickhelper.entities.Message;
import smart.quickhelper.entities.MessageLine;
import smart.quickhelper.entities.Task;
import smart.quickhelper.entities.Thread;
import smart.quickhelper.entities.ThreadLine;
import smart.quickhelper.entities.User;
import smart.quickhelper.exceptions.MessageException;

public class MessageServiceBase extends ServiceBase implements IMessageService {

	@Override
	public Message GetMessageById(long messageId) {
		// try (SessionFactory sf = new
		// Configuration().configure().buildSessionFactory()) {
		try (Session session = getSessionFactory().openSession()) {
			Message message = session.find(Message.class, messageId);
			return message;
		}
		// }
	}

	@Override
	public void SendMessage(User sender, List<User> recipients, Message message) {
		SendMessage(null, sender, recipients, message);
	}

	@Override
	public void SendMessage(Task task, User sender, List<User> recipients, Message message) {
		// set parent
		message.setParent(null);
		try (Session session = getSessionFactory().openSession()) {
			session.beginTransaction();
			// create a new thread
			Thread thread = new Thread();
			session.save(thread);
			session.getTransaction().commit();
			try {
				this.SendMessage(task, sender, recipients, message, thread);
			} catch (Exception ex) {
				session.getTransaction().rollback();
				throw ex;
			}
		}
	}

	protected void SendMessage(Task task, User sender, List<User> recipients, Message message, Thread thread) {
		try (Session session = getSessionFactory().openSession()) {
			session.beginTransaction();
			// add task
			if (task != null) {
				message.setTask(task);
			}
			// set thread
			message.setThread(thread);
			// add participant
			ThreadLine threadline = new ThreadLine();
			threadline.setThread(thread);
			threadline.setParticipant(sender);
			session.save(threadline);
			// thread.getThreadLines().add(threadline);
			// save message
			message.setSender(sender);
			session.save(message);
			// add recipients
			for (User recipient : recipients) {
				// add message line
				MessageLine messageline = new MessageLine();
				messageline.setMessage(message);
				messageline.setRecipient(recipient);
				session.save(messageline);
				message.getMessageLines().add(messageline);
			}
			session.getTransaction().commit();
		}
	}

	@Override
	public void ReplyMessage(User sender, Message parent, Message message) throws MessageException {
		if (parent == null) {
			throw new MessageException("parent message is required for replying");
		}
		// set thread
		Thread thread = parent.getThread();
		// set parent
		message.setParent(parent);
		// set participant
		// set recipient
		List<User> recipients = new ArrayList<>();
		recipients.add(parent.getSender());
		// set task
		Task task = parent.getTask();
		// send message
		SendMessage(task, sender, recipients, message, thread);
	}

	@Override
	public void ReplyMessage(User sender, MessageLine parentMessageLine, Message message) throws MessageException {
		Message parent = parentMessageLine.getMessage();
		ReplyMessage(sender, parent, message);
	}

	@Override
	public void ForwardMessage(User sender, List<User> recipients, Message parent, Message message)
			throws MessageException {
		// set thread
		Thread thread = parent.getThread();
		// set parent
		message.setParent(parent);
		// set task
		Task task = parent.getTask();
		// sent message
		this.SendMessage(task, sender, recipients, message, thread);
	}

	@Override
	public Collection<Message> GetMessagesByUserAndThread(long threadId) {
		try (Session session = getSessionFactory().openSession()) {
			Thread thread = session.find(Thread.class, threadId);
			if (thread == null) {
				return new ArrayList<Message>();
			}
			return thread.getMessages();
		}
	}

	@Override
	public Collection<Message> GetAllMessagesByUser(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Message> GetReceivedMessagesByUser(long userId) {
		return GetReceivedMessagesByUser(userId, new Date(Long.MIN_VALUE), new Date(Long.MAX_VALUE));
	}

	@Override
	public Message GetFirstMessageByThread(long threadId) {
		try (Session session = getSessionFactory().openSession()) {
			Thread thread = session.find(Thread.class, threadId);
			if (thread == null) {
				return null;
			}
			Message message = thread.getMessages().stream().filter(m -> m.getParent() == null).findFirst().orElse(null);
			return message;
		}
	}

	@Override
	public Collection<Message> GetSentMessagesByUser(long userId) {
		return GetSentMessagesByUser(userId, new Date(Long.MIN_VALUE), new Date(Long.MAX_VALUE));
	}

	protected Collection<Message> GetSentMessagesByUser(long userId, Date startTime, Date endTime) {
		try (Session session = getSessionFactory().openSession()) {
			List<Message> messages = session.createCriteria(Message.class).list();
			List<Message> result = messages.stream()
					.filter(message -> message.getSender().getId() == userId
							&& message.getCreatedTime().compareTo(startTime) >= 0
							&& message.getCreatedTime().compareTo(endTime) < 0)
					.sorted((i1, i2) -> i1.getCreatedTime().compareTo(i2.getCreatedTime()))
					.collect(Collectors.toList());
			return result;
		}
	}

	protected Collection<Message> GetReceivedMessagesByUser(long userId, Date startTime, Date endTime) {
		try (Session session = getSessionFactory().openSession()) {
			Set<Message> result = new HashSet<>();
			List<MessageLine> messagelines = session.createCriteria(MessageLine.class).list();
			List<MessageLine> selectedMessageLines = messagelines.stream()
					.filter(messageline -> messageline.getRecipient().getId() == userId
							&& messageline.getMessage().getCreatedTime().compareTo(startTime) >= 0
							&& messageline.getMessage().getCreatedTime().compareTo(endTime) < 0)
					.sorted((i1, i2) -> i1.getMessage().getCreatedTime().compareTo(i2.getMessage().getCreatedTime()))
					.collect(Collectors.toList());
			for (MessageLine messageline : selectedMessageLines) {
				result.add(messageline.getMessage());
			}
			return result;
		}
	}

	@Override
	public Collection<Thread> GetThreadsByTask(long taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Message> GetMessagesByTask(long taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Message> GetMessagesByThread(long threadId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Thread> GetParticipatedThreadsByUser(long userId) {
		try (Session session = getSessionFactory().openSession()) {
			Collection<ThreadLine> threadlines = session.createCriteria(ThreadLine.class).list();
			Collection<ThreadLine> selectedThreadlines = threadlines.stream()
					.filter(t -> t.getParticipant().getId() == userId).collect(Collectors.toList());
			Set<Thread> threads = new HashSet<Thread>();
			//
			for (ThreadLine threadline : selectedThreadlines) {
				threads.add(threadline.getThread());
			}
			return threads;
		}
	}

	@Override
	public Collection<Message> GetParticipatedFirstMessagesByUser(long userId) {
		Set<Message> messages = new HashSet<>();
		Collection<Thread> threads = GetParticipatedThreadsByUser(userId);
		for (Thread thread : threads) {
			Message message = GetFirstMessageByThread(thread.getId());
			messages.add(message);
		}
		return messages;
	}

	@Override
	public Collection<Message> GetFirstMessagesInitiatedByUser(long userId) {
		Set<Message> messages = new HashSet<>();
		Collection<Thread> threads = GetParticipatedThreadsByUser(userId);
		for (Thread thread : threads) {
			Message message = GetFirstMessageByThread(thread.getId());
			if (message.getSender().getId() == userId) {
				messages.add(message);
			}
		}
		return messages;
	}

	@Override
	public Collection<Thread> GetThreadsByUser(long userId) {
		Collection<Message> messages = GetFirstMessagesByUser(userId);
		//
		Set<Thread> threads = new HashSet<>();
		for (Message message : messages) {
			threads.add(message.getThread());
		}
		return threads;
	}

	@Override
	public Collection<Message> GetFirstMessagesByUser(long userId) {
		try (Session session = getSessionFactory().openSession()) {
			Collection<Message> result = new HashSet<>();
			// get initiated messages
			List<Message> messages = session.createCriteria(Messages.class).list();
			List<Message> initiatedMessages = messages.stream().filter(m -> m.getId() == userId)
					.collect(Collectors.toList());
			// get received messages
			List<MessageLine> messagelines = session.createCriteria(MessageLine.class).list();
			List<Message> receivedMessages = messagelines.stream().filter(m -> m.getId() == userId)
					.map(m -> m.getMessage()).collect(Collectors.toList());
			//
			for (Message message : initiatedMessages) {
				messages.add(message);
			}
			for (Message message : receivedMessages) {
				messages.add(message);
			}
			return messages;
		}
	}

	@Override
	public Message GetMessageByMessageLine(long messageLineId) {
		try (Session session = getSessionFactory().openSession()) {
			MessageLine messageline = session.find(MessageLine.class, messageLineId);
			return messageline.getMessage();
		}
	}

	@Override
	public void MarkRead(long messageLineId) {
		try (Session session = getSessionFactory().openSession()) {
			session.beginTransaction();
			MessageLine messageline = session.find(MessageLine.class, messageLineId);
			messageline.setIsRead(true);
			messageline.setReadTime(new Date());
			session.update(messageline);
			session.getTransaction().commit();
		}
	}

	@Override
	public Thread GetThreadById(long threadId) {
		try (Session session = getSessionFactory().openSession()) {
			Thread thread = session.find(Thread.class, threadId);
			return thread;
		}
	}

}
