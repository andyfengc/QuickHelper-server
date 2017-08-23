package smart.quickhelper.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import smart.quickhelper.entities.Category;
import smart.quickhelper.entities.User;

public class CategoryServiceBase extends ServiceBase implements ICategoryService {

	@Override
	public void AddCategory(Category category) {
		try (Session session = getSessionFactory().openSession()) {
			session.beginTransaction();
			session.save(category);
			session.getTransaction().commit();
		}
	}

	@Override
	public Category GetCategoryById(long categoryId) {
		try (Session session = getSessionFactory().openSession()) {
			Category category = session.find(Category.class, categoryId);
			return category;
		}
	}

	@Override
	public List<Category> GetAllCategories() {
		try (Session session = getSessionFactory().openSession()) {
			List<Category> result = session.createCriteria(Category.class).list();
			return result;
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
		try (Session session = getSessionFactory().openSession()) {
			session.beginTransaction();
			session.delete(category);
			session.getTransaction().commit();
		}
	}

	@Override
	public void UpdateCategory(Category category) {
		try (Session session = getSessionFactory().openSession()) {
			session.beginTransaction();
			session.update(category);
			session.getTransaction().commit();
		}
	}

}
