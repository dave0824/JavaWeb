package cn.itcast.bookstore.category;

import java.util.List;

import cn.itcast.bookstore.admin.category.CategoryExcepiton;
import cn.itcast.bookstore.book.BookDao;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();
	private BookDao bookDao = new BookDao();
	public List<Category> all() {
		return categoryDao.all();
	}
	
	public Category findByCid(String cid) {
		return categoryDao.findByCid(cid);
	}
	
	public void del(String cid) throws CategoryExcepiton {
		int count = bookDao.count(cid);
		if(count > 0) {
			throw new CategoryExcepiton("该分类下还有图书，不能删除！");
		}
		categoryDao.del(cid);
	}
	
	public void mod(Category category) {
		categoryDao.mod(category);
	}
	
	public void add(Category category) throws CategoryExcepiton {
		Category _category = categoryDao.findByCname(category.getCname());
		if(_category != null) {
			throw new CategoryExcepiton("该分类已经存在！");
		}
		categoryDao.add(category);
	}
}
