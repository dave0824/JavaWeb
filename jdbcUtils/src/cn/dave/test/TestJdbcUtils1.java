/**
 * 
 */
package cn.dave.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import cn.dave.jdbcUtils1.JdbcUtils;

/**
 * @author dave
 *
 */
public class TestJdbcUtils1 {

	@Test
	public void test() throws SQLException {
		Connection con = JdbcUtils.getConnection();
		//Statement stmt = con.createStatement();
		System.out.println(con);
	}

}
