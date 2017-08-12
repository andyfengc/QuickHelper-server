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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void DeleteCategory(long categoryId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DeleteCategory(Category category) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UpdateCategory(Category category) {
		// TODO Auto-generated method stub
		
	}

}
