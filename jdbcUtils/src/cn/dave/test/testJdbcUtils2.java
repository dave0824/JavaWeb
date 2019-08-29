/**
 * 
 */
package cn.dave.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

import cn.dave.jdbcUtils2.JdbcUtils;

/**
 * @author dave
 *
 */
public class testJdbcUtils2 {

	@Test
	public void test() throws SQLException {
		Connection con = JdbcUtils.getConnection();
		System.out.println("haha");
		System.out.println(con);
		DataSource ds = JdbcUtils.getDataSource();
		System.out.println(ds);
	}

}
