/**
 * 
 */
package cn.dave.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import cn.dave.jdbcUtils.JdbcUtils;

/**
 * 批处理
 * @author dave
 *
 */
public class TestBatch {

	@Test
	public void test() throws SQLException {
		Connection con = JdbcUtils.getConnection();
		String sql = "INSERT  users2 VALUES(?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		for(int i=1;i<9999;i++){
			pstmt.setInt(1, i);
			pstmt.setString(2, "stu_"+i);
			pstmt.setString(3, "pass_"+i);
			pstmt.addBatch();
		}
		pstmt.executeBatch();
	}

}
