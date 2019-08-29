package cn.dave.bookstore.admin.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.dave.bookstore.admin.domain.Admin;
import cn.dave.bookstore.user.domain.User;
import cn.itcast.jdbc.TxQueryRunner;

public class AdminDao {

	QueryRunner qr = new TxQueryRunner();

	public void add(Admin form) {
		try {
			String sql = "insert into admin values(?,?,?,?)";
			Object[] params = {form.getAid(), form.getAname(), 
					form.getPassword(),form.isState()};
			qr.update(sql, params);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * 通过用户名查找
	 * @author: dave
	 * @date:   2019年5月13日 上午11:09:43
	 * @Description: TODO
	 * @param aname
	 * @return Admin  
	 * @throws
	 */
	public Admin findByAname(String aname) {
		try{
			String sql = "SELECT * FROM admin WHERE aname=?";
			return  qr.query(sql, new BeanHandler<Admin>(Admin.class),aname);
		
		}catch(SQLException e){
			throw new RuntimeException(e);
		
		}
	}
}
