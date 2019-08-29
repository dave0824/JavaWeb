package cn.dave.book.service;

import java.util.List;

import cn.dave.book.dao.BookDao;
import cn.dave.book.domain.Book;

public class BookService {
	private BookDao bookDao = new BookDao();

	public List<Book> findAll() {
		// TODO Auto-generated method stub
		return bookDao.findAll();
	}

	public List<Book> findByCategory(int category) {
		// TODO Auto-generated method stub
		return bookDao.findByCategory(category);
	}
}
