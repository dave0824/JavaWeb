package cn.itcast.bookstore.book;

import java.util.List;

public class BookService {
	private BookDao bookDao = new BookDao();
	
	public List<Book> all() {
		return bookDao.all();
	}
	
	public List<Book> query(String cid) {
		return bookDao.query(cid);
	}
	
	public Book load(String bid) {
		return bookDao.load(bid);
	}
	
	public void add(Book book) {
		bookDao.add(book);
	}
	
	public void mod(Book book) {
		bookDao.mod(book);
	}
	
	public void del(String bid) {
		bookDao.del(bid);
	}
}
