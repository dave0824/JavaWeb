package cn.dave.bookstore.category.service;

import java.util.List;

import cn.dave.bookstore.book.domain.Book;
import cn.dave.bookstore.category.dao.CategoryDao;
import cn.dave.bookstore.category.domain.Category;

public class CategoryService {
	CategoryDao categoryDao = new CategoryDao();

	/**
	 * 查找所有图书分类
	 * @author: dave
	 * @date:   2019年5月6日 下午6:00:24
	 * @Description: TODO
	 * @return Category  
	 * @throws
	 */
	public List<Category> findAll() {
		
		return categoryDao.findAll();
	}

	/**
	 * 添加分类
	 * @author: dave
	 * @date:   2019年5月10日 下午10:12:13
	 * @Description: 添加分类，若添加分类存在则打回之
	 * @param category void  
	 * @throws
	 */
	public void add(Category category) throws CategoryException {
		/*
		 * 1.调用dao.findByCname方法得到cg
		 * 2.如果cg不为null，抛异常
		 * 3.调用dao.add方法，添加分类	
		 */
		Category cg = categoryDao.findByCname(category.getCname());
		if(cg != null) throw new CategoryException("您要添加的分类已存在，请您不要重复添加");
		categoryDao.add(category);
		
	}

	/**
	 * 根据cid查找类别
	 * @author: dave
	 * @date:   2019年5月10日 下午10:37:23
	 * @Description: TODO
	 * @param parameter
	 * @return Object  
	 * @throws
	 */
	public Category findByCid(String cid) {
		
		return categoryDao.findByCid(cid);
	}

	/**
	 * @throws CategoryException 
	 * 修改类别名称
	 * @author: dave
	 * @date:   2019年5月10日 下午10:46:30
	 * @Description: TODO
	 * @param category void  
	 * @throws
	 */
	public void edit(Category category) throws CategoryException {
		/*
		 * 1.调用dao.findByCname方法得到cg
		 * 2.如果cg不为null，抛异常
		 * 3.调用dao.add方法，添加分类	
		 */
		Category cg = categoryDao.findByCname(category.getCname());
		if(cg != null) throw new CategoryException("您修改的分类已存在，请您换一个名称");
		categoryDao.edit(category);
		
	}

	public void delete(String cid) throws CategoryException {
		/*
		 * 1.调用dao.findByBookCid方法得到bookList
		 * 2.如果bookList不为null，抛异常
		 * 3.调用dao.delete方法，删除分类	
		 */
		List<Book> bookList = categoryDao.findByBookCname(cid);
		if(!bookList.isEmpty() || bookList.size() != 0) throw new CategoryException("删除失败，您要删除的分类已存在所属图书");
		categoryDao.delete(cid);
		
	}
}
