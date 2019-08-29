package cn.itcast.bookstore.user;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.jdbc.util.JdbcUtils;

public class UserDao {
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());

	public User findByUsername(String username) {
		String sql = "select * from user where username=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), username);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public User findByEmail(String email) {
		String sql = "select * from user where email=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), email);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public User findByCode(String code) {
		String sql = "select * from user where code=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), code);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void add(User user) {
		String sql = "insert into user values(?,?,?,?,?,?)";
		try {
			qr.update(sql, user.getUid(), user.getUsername(),
					user.getPassword(), user.getEmail(), user.isState(),
					user.getCode());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void updateState(String uid, boolean state) {
		String sql = "update user set state=? where uid=?";
		try {
			qr.update(sql, state, uid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
