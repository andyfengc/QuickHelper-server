package smart.quickhelper.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import smart.quickhelper.entities.Category;
import smart.quickhelper.entities.User;

public class CategoryServiceBase implements ICategoryService {

	@Override
	public void AddCategory(Category category) {
		try (SessionFactory sf = new Configuration().configure().buildSessionFactory()) {
			try (Session session = sf.openSession()) {
				session.beginTransaction();
				session.save(category);
				session.getTransaction().commit();
			}
		}
	}

	@Override
	public Category GetCategoryById(long categoryId) {
		try (SessionFactory sf = new Configuration().configure().buildSessionFactory()) {
			try (Session session = sf.openSession()) {
				Category category = session.find(Category.class, categoryId);
				return category;
			}
		}
	}

	@Override
	public List<Category> GetAllCategories() {
		try (SessionFactory sf = new Configuration().configure().buildSessionFactory()) {
			try (Session session = sf.openSession()) {
				List<Category> result = session.createCriteria(Category.class).list();
				return result;
			}
		}
	}

	@Override
	public void DeleteCategory(long categoryId) {
		Category category = GetCategoryById(categoryId);
		if (category != null) {
			DeleteCategory(category);
		}
	}

	@Override
	public void DeleteCategory(Category category) {
		try (SessionFactory sf = new Configuration().configure().buildSessionFactory()) {
			try (Session session = sf.openSession()) {
				session.beginTransaction();
				session.delete(category);
				session.getTransaction().commit();
			}
		}
	}

	@Override
	public void UpdateCategory(Category category) {
		try (SessionFactory sf = new Configuration().configure().buildSessionFactory()) {
			try (Session session = sf.openSession()) {
				session.beginTransaction();
				session.update(category);
				session.getTransaction().commit();
			}
		}
	}

}
