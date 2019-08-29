/**
 * 
 */
package cn.dave.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import cn.dave.dao.AccountDao;
import cn.dave.jdbcUtils3.JdbcUtils;

/**
 * @author dave
 *
 */
public class TestJdbcUtils3 {
	AccountDao dao = new AccountDao();
	@Test
	public void test()throws Exception {
		try {
			JdbcUtils.beginTransaction();
			
			dao.update("小红", -100);
			
			//if(true) throw new RuntimeException();
			
			dao.update("小明", 100);
			
			JdbcUtils.commitTransaction();
		} catch (Exception e) {
			try {
				System.out.println("eeeee");
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
			}
				throw e;
		}
	}
}

