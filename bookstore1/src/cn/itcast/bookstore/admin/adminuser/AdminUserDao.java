package cn.itcast.bookstore.admin.adminuser;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.jdbc.util.JdbcUtils;

public class AdminUserDao {
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());

	public AdminUser login(String adminname, String password) {
		try {
			String sql = "select * from adminuser where adminname=? and password=?";
			return qr.query(sql, new BeanHandler<AdminUser>(AdminUser.class), adminname, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
