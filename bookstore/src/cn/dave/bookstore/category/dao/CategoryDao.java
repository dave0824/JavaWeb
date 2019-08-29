package cn.dave.bookstore.category.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.dave.bookstore.book.domain.Book;
import cn.dave.bookstore.category.domain.Category;
import cn.itcast.jdbc.TxQueryRunner;

public class CategoryDao {
	QueryRunner qr = new TxQueryRunner();

	/**
	 * 查询所有的分类
	 * @author: dave
	 * @date:   2019年5月6日 下午6:03:40
	 * @Description: TODO
	 * @return List<Category>  
	 * @throws
	 */
	public List<Category> findAll() {
		try{
			String sql = "SELECT * FROM category";
			return qr.query(sql, new BeanListHandler<Category>(Category.class));
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据名字查找分类
	 * @author: dave
	 * @date:   2019年5月10日 下午10:21:19
	 * @Description: TODO
	 * @param cname
	 * @return Category  
	 * @throws
	 */
	public Category findByCname(String cname) {
		try{
			String sql = "SELECT * FROM category WHERE cname=?";
			return qr.query(sql, new BeanHandler<Category>(Category.class),cname);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加类别
	 * @author: dave
	 * @date:   2019年5月10日 下午10:23:57
	 * @Description: TODO
	 * @param category void  
	 * @throws
	 */
	public void add(Category category) {
		
		try{
			String sql = "INSERT INTO category VALUES(?,?)";
			qr.update(sql,category.getCid(),category.getCname());
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 通过cid查找category
	 * @author: dave
	 * @date:   2019年5月10日 下午10:38:22
	 * @Description: TODO
	 * @param cid
	 * @return Object  
	 * @throws
	 */
	public Category findByCid(String cid) {
		try{
			String sql = "SELECT * FROM category WHERE cid=?";
			return qr.query(sql, new BeanHandler<Category>(Category.class),cid);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改类别名称
	 * @author: dave
	 * @date:   2019年5月10日 下午10:47:49
	 * @Description: TODO
	 * @param category void  
	 * @throws
	 */
	public void edit(Category category) {
		try{
			String sql = "UPDATE category SET cname=? WHERE cid=?";
			qr.update(sql,category.getCname(),category.getCid());
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * 根据cid查找图书
	 * @author: dave
	 * @date:   2019年5月10日 下午11:04:08
	 * @Description: TODO
	 * @param cid
	 * @return List<Book>  
	 * @throws
	 */
	public List<Book> findByBookCname(String cid) {
		try{
			String sql = "SELECT * FROM book WHERE cid=?";
			return qr.query(sql, new BeanListHandler<Book>(Book.class),cid);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除分类
	 * @author: dave
	 * @date:   2019年5月10日 下午11:06:25
	 * @Description: TODO
	 * @param cid void  
	 * @throws
	 */
	public void delete(String cid) {
		
		try{
			String sql = "DELETE FROM category WHERE cid=?";
			qr.update(sql,cid);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
