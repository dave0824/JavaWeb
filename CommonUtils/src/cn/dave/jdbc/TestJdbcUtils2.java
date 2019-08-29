/**
 * 
 */
package cn.dave.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

/**
 * @author dave
 *
 */
public class TestJdbcUtils2 {

	@Test
	public void test() throws SQLException {
		Connection con = JdbcUtils.getConnection();
		System.out.println("haha");
		System.out.println(con);
		DataSource ds = JdbcUtils.getDataSource();
		System.out.println(ds);
	}

}
