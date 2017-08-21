package smart.quickhelper.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.NotSupportedException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import smart.quickhelper.entities.Message;
import smart.quickhelper.entities.MessageLine;
import smart.quickhelper.entities.Task;
import smart.quickhelper.entities.Thread;
import smart.quickhelper.entities.ThreadLine;
import smart.quickhelper.entities.User;
import smart.quickhelper.exceptions.MessageException;

public class MessageServiceBase implements IMessageService {

	@Override
	public Message GetMessageById(long messageId) {
		try (SessionFactory sf = new Configuration().configure().buildSessionFactory()) {
			try (Session session = sf.openSession()) {
				Message message = session.find(Message.class, messageId);
				return message;
			}
		}
	}

	@Override
	public void SendMessage(User sender, List<User> recipients, Message message) {
		SendMessage(null, sender, recipients, message);
	}

	@Override
	public void SendMessage(Task task, User sender, List<User> recipients, Message message) {
		// set parent
		message.setParent(null);
		try (SessionFactory sf = new Configuration().configure().buildSessionFactory()) {
			try (Session session = sf.openSession()) {
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
	}

	protected void SendMessage(Task task, User sender, List<User> recipients, Message message, Thread thread) {
		try (SessionFactory sf = new Configuration().configure().buildSessionFactory()) {
			try (Session session = sf.openSession()) {
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
	public List<Message> GetMessagesByUserAndThread(long userId, long threadId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> GetAllMessagesByUser(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> GetReceivedMessagesByUser(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message GetFirstMessageByThread(long threadId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> GetSentMessagesByUser(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Thread> GetThreadsByTask(long taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> GetMessagesByTask(long taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> GetMessagesByThread(long threadId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Thread> GetParticipatedThreadsByUser(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> GetParticipatedFirstMessagesByUser(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> GetFirstMessagesInitiatedByUser(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Thread> GetThreadsByUser(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> GetFirstMessagesByUser(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message GetMessageByMessageLine(long messageLineId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void MarkRead(long messageLineId) {
		// TODO Auto-generated method stub

	}

}
