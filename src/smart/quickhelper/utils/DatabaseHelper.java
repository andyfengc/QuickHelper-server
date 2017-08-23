package smart.quickhelper.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseHelper {
	private static DatabaseHelper instance;
	private static SessionFactory sessionFactory;
	private DatabaseHelper() {
	}
	public static DatabaseHelper getInstance() {
		if (instance == null) {
			instance = new DatabaseHelper();
		}
		return instance;
	}
	
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		return sessionFactory;
	}
}
