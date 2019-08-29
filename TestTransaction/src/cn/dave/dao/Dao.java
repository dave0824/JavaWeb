/**
 * 
 */
package cn.dave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.dave.jdbcUtils.JdbcUtils;

/**
 * @author dave
 *
 */
public class Dao {
	public void updataBalance(Connection con, String name,double balance) {
		PreparedStatement pstmt=null;
		try{
			String sql ="UPDATE account SET balance=balance+? WHERE name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setDouble(1, balance);
			pstmt.setString(2, name);
			pstmt.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			try {
				if(pstmt != null)pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
