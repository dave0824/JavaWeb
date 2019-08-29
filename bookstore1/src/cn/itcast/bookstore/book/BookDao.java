package cn.itcast.bookstore.book;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.bookstore.category.Category;
import cn.itcast.jdbc.util.JdbcUtils;
import cn.itcast.utils.CommonUtils;

public class BookDao {
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());

	public void add(Book book) {
		String sql = "insert into book value(?,?,?,?,?,?,?)";
		try {
			qr.update(sql, book.getBid(), book.getBname(), book.getPrice(), book
					.getAuthor(), book.getImage(), book.getCategory().getCid(), false);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void mod(Book book) {
		String sql = "update book set bname=?,price=?,author=?,cid=? where bid=?";
		try {
			qr.update(sql, book.getBname(), book.getPrice(), book.getAuthor(), book.getCategory().getCid(), book.getBid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void del(String bid) {
		String sql = "update book set isdel=true where bid=?";
		try {
			qr.update(sql, bid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Book> all() {
		String sql = "select * from book where isdel=false";
		try {
			return qr.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int count(String cid) {
		String sql = "select count(*) from book where cid=? and isdel=false";
		try {
			Number number = (Number) qr.query(sql, new ScalarHandler(), cid);
			return number.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Book> query(String cid) {
		String sql = "select * from book where cid=? and isdel=false";
		try {
			return qr.query(sql, new BeanListHandler<Book>(Book.class), cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Book load(String bid) {
		String sql = "select * from book b, category c where b.cid=c.cid and bid=? and isdel=false";
		try {
			Map<String,Object> result = qr.query(sql, new MapHandler(), bid);
			Book book = CommonUtils.toBean(result, Book.class);
			Category category = CommonUtils.toBean(result, Category.class);
			book.setCategory(category);
			return book;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
