package cn.itcast.bookstore.order;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import cn.itcast.bookstore.book.Book;
import cn.itcast.jdbc.util.JdbcUtils;
import cn.itcast.utils.CommonUtils;

public class OrderDao {
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());

	public void updateAddress(String oid, String address) {
		try {
			String sql = "update orders set address=? where oid=?";
			qr.update(sql, address, oid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void updateState(String oid, int state) {
		try {
			String sql = "update orders set state=? where oid=?";
			qr.update(sql, state, oid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void add(Order order) {
		try {
			String sql = "insert into orders values(?,?,?,?,?,?)";
			qr.update(sql, order.getOid(), order.getTotal(), new Timestamp(
					order.getOrdertime().getTime()), order.getState(), order
					.getAddress(), order.getUser().getUid());

			Set<OrderItem> orderItemSet = order.getOrderItemSet();
			sql = "insert into orderitem values(?,?,?,?,?)";
			Object[][] params = new Object[orderItemSet.size()][];
			int i = 0;
			for (OrderItem item : orderItemSet) {
				params[i++] = new Object[] { item.getItemid(), item.getCount(),
						item.getSubtotal(), item.getBook().getBid(),
						item.getOrder().getOid() };
			}
			qr.batch(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Order load(String oid) {
		try {
			String sql = "select * from orders where oid=?";
			Order order = qr.query(sql, new BeanHandler<Order>(Order.class), oid);
			sql = "SELECT * FROM orderitem oi, book b WHERE oi.bid=b.bid AND oid=?";
			List<Map<String, Object>> results = qr.query(sql, new MapListHandler(), order.getOid());
			for (Map<String, Object> result : results) {
				Book book = CommonUtils.toBean(result, Book.class);
				OrderItem orderItem = CommonUtils.toBean(result, OrderItem.class);
				orderItem.setBook(book);
				order.addOrderItem(orderItem);
			}
			return order;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Order> orderList(String uid) {
		try {
			String sql = "select * from orders where uid=? order by ordertime desc";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(
					Order.class), uid);
			for (Order order : orderList) {
				sql = "SELECT * FROM orderitem oi, book b WHERE oi.bid=b.bid AND oid=?";
				List<Map<String, Object>> results = qr.query(sql,
						new MapListHandler(), order.getOid());
				for (Map<String, Object> result : results) {
					Book book = CommonUtils.toBean(result, Book.class);
					OrderItem orderItem = CommonUtils.toBean(result,
							OrderItem.class);
					orderItem.setBook(book);
					order.addOrderItem(orderItem);
				}
			}
			return orderList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Order> all() {
		try {
			String sql = "select * from orders order by ordertime desc";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class));
			for (Order order : orderList) {
				sql = "SELECT * FROM orderitem oi, book b WHERE oi.bid=b.bid AND oid=?";
				List<Map<String, Object>> results = qr.query(sql,
						new MapListHandler(), order.getOid());
				for (Map<String, Object> result : results) {
					Book book = CommonUtils.toBean(result, Book.class);
					OrderItem orderItem = CommonUtils.toBean(result,
							OrderItem.class);
					orderItem.setBook(book);
					order.addOrderItem(orderItem);
				}
			}
			return orderList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Order> findByState(int state) {
		try {
			String sql = "select * from orders where state=? order by ordertime desc";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class), state);
			for (Order order : orderList) {
				sql = "SELECT * FROM orderitem oi, book b WHERE oi.bid=b.bid AND oid=?";
				List<Map<String, Object>> results = qr.query(sql,
						new MapListHandler(), order.getOid());
				for (Map<String, Object> result : results) {
					Book book = CommonUtils.toBean(result, Book.class);
					OrderItem orderItem = CommonUtils.toBean(result,
							OrderItem.class);
					orderItem.setBook(book);
					order.addOrderItem(orderItem);
				}
			}
			return orderList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
