package smart.quickhelper.services;

import java.util.List;

import smart.quickhelper.entities.Category;

public interface ICategoryService {
	public void AddCategory(Category category);
	public Category GetCategoryById(long categoryId);
	public List<Category> GetAllCategories();
	public void DeleteCategory(long categoryId);
	public void DeleteCategory(Category category);
	public void UpdateCategory(Category category);
}
