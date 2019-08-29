/**
 * 
 */
package cn.dave.jdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/**
 * @author dave
 *
 */
public class TestJdbcUtils {

	@Test
	public void test() throws SQLException {
		Connection con = JdbcUtils.getConnection();
		//Statement stmt = con.createStatement();
		System.out.println(con);
	}

}
