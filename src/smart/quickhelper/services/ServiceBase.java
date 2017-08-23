package smart.quickhelper.services;

import java.io.IOException;

import org.hibernate.SessionFactory;

import smart.quickhelper.utils.DatabaseHelper;

public class ServiceBase implements AutoCloseable {

	protected SessionFactory getSessionFactory() {
		return DatabaseHelper.getSessionFactory();
	}

	@Override
	public void close() throws IOException {
		DatabaseHelper.getSessionFactory().close();
	}
}
