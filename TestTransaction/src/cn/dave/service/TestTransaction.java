/**
 * 
 */
package cn.dave.service;

import java.sql.Connection;
import java.sql.SQLException;

import cn.dave.dao.Dao;
import cn.dave.jdbcUtils.JdbcUtils;

/**
 * @author dave
 *
 */
public class TestTransaction {
	/**
	 * 转账
	 * */
	public void zhuanzhang(String from,String to,double money){
		Connection con = null;
		try{
			con = JdbcUtils.getConnection();
			//开启事务
			con.setAutoCommit(false);
			Dao dao = new Dao();
			dao.updataBalance(con, from, -money);
			dao.updataBalance(con, to, money);
			//关闭事务
			con.commit();
			
		}catch(SQLException e){
			//回滚事务
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
