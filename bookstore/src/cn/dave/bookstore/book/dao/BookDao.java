package cn.dave.bookstore.book.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import cn.dave.bookstore.book.domain.Book;
import cn.dave.bookstore.category.domain.Category;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class BookDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 查找所有图书
	 * @author: dave
	 * @date:   2019年5月6日 下午6:40:37
	 * @Description: TODO
	 * @return List<Book>  
	 * @throws
	 */
	public List<Book> findAll() {
		try{
			String sql = "SELECT * FROM book WHERE del='0'";
			return qr.query(sql, new BeanListHandler<Book>(Book.class));
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按分类查找图书
	 * @author: dave
	 * @date:   2019年5月6日 下午6:40:55
	 * @Description: TODO
	 * @param cid
	 * @return List<Book>  
	 * @throws
	 */
	public List<Book> findByCid(String cid) {
		try{
			String sql = "SELECT * FROM book WHERE cid=? AND del='0'";
			return qr.query(sql, new BeanListHandler<Book>(Book.class),cid);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

/*	*//**
	 * 根据bid查找图书
	 * @author: dave
	 * @date:   2019年5月11日 下午2:53:09
	 * @Description: TODO
	 * @param bid
	 * @return Book  
	 * @throws
	 *//*
	public Book findByBid(String bid) {
		try{
			String sql = "SELECT * FROM book WHERE bid=?";
			return qr.query(sql, new BeanHandler<Book>(Book.class),bid);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}*/
	
	/**
	 * 根据bid查找图书，并将类别加上
	 * @author: dave
	 * @date:   2019年5月11日 下午2:53:54
	 * @Description: TODO
	 * @param bid
	 * @return Book  
	 * @throws
	 */
	public Book findByBid(String bid) {
		try{
			String sql = "SELECT * FROM book WHERE bid=?";
			Map<String,Object> map = qr.query(sql, new MapHandler(),bid);
			Book book = CommonUtils.toBean(map, Book.class);
			Category category = CommonUtils.toBean(map, Category.class);
			book.setCategory(category);
			return book;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加图书
	 * @author: dave
	 * @date:   2019年5月11日 下午3:54:38
	 * @Description: TODO
	 * @param book void  
	 * @throws
	 */
	public void add(Book book) {
		try {
			String sql = "INSERT INTO book VALUES(?,?,?,?,?,?,?)";
			Object[] params = {book.getBid(), book.getBname(), book.getPrice(),
					book.getAuthor(), book.getImage(), book.getCategory().getCid(),book.isDel()};
			qr.update(sql, params);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	//删除图书
	public void delete(String bid) {
		
		try {
			String sql = "UPDATE book SET del='1' WHERE bid=?";
			qr.update(sql,bid);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改图书
	 * @author: dave
	 * @date:   2019年5月12日 下午5:05:21
	 * @Description: TODO
	 * @param book void  
	 * @throws
	 */
	public void modify(Book book) {
		
		try {
			String sql = "UPDATE book SET bname=?,price=?,author=?,cid=? WHERE bid=?";
			Object[] params = {book.getBname(), book.getPrice(),
					book.getAuthor(), book.getCategory().getCid(),book.getBid()};
			qr.update(sql, params);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
