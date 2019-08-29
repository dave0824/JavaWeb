package cn.dave.bookstore.book.service;

import java.util.List;

import cn.dave.bookstore.book.dao.BookDao;
import cn.dave.bookstore.book.domain.Book;

public class BookService {
	private BookDao bookDao = new BookDao();

	/**
	 * 查找所有图书
	 * @author: dave
	 * @date:   2019年5月6日 下午6:39:57
	 * @Description: TODO
	 * @return List<Book>  
	 * @throws
	 */
	public List<Book> findAll() {
	
		return bookDao.findAll();
	}

	/**
	 * 按分类查找图书
	 * @author: dave
	 * @date:   2019年5月6日 下午6:40:08
	 * @Description: TODO
	 * @param cid
	 * @return List<Book>  
	 * @throws
	 */
	public List<Book> findByCid(String cid) {
	
		return bookDao.findByCid(cid);
	}

	/**
	 * 查找一本图书
	 * @author: dave
	 * @date:   2019年5月6日 下午6:46:44
	 * @Description: TODO
	 * @param bid
	 * @return Book  
	 * @throws
	 */
	public Book findByBid(String bid) {
		
		return bookDao.findByBid(bid);
	}

	/**
	 * 添加图书
	 * @author: dave
	 * @date:   2019年5月11日 下午3:53:43
	 * @Description: TODO
	 * @param book void  
	 * @throws
	 */
	public void add(Book book) {
		bookDao.add(book);
		
	}

	/**
	 * 删除图书
	 * @author: dave
	 * @date:   2019年5月12日 下午4:26:17
	 * @Description: TODO
	 * @param bid void  
	 * @throws
	 */
	public void delete(String bid) {
		
		bookDao.delete(bid);
		
	}

	/**
	 * 修改图书
	 * @author: dave
	 * @date:   2019年5月12日 下午5:04:28
	 * @Description: TODO
	 * @param book void  
	 * @throws
	 */
	public void modify(Book book) {
		bookDao.modify(book);
		
	}
}
