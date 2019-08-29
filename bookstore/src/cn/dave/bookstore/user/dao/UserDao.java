package cn.dave.bookstore.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.dave.bookstore.user.domain.User;
import cn.itcast.jdbc.TxQueryRunner;

public class UserDao {
	private QueryRunner qr = new TxQueryRunner();
	
	/**
	 * 
	* @Title: findByUsername 
	* @Description: 通过名字查找用户 
	* @param  username
	* @return User
	* @throws
	 */
	public User findByUsername(String username) {
		
		try{
			String sql = "SELECT * FROM tb_user WHERE username=?";
			return  qr.query(sql, new BeanHandler<User>(User.class),username);
		
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	* @Title: findByEmail 
	* @Description: 通过邮箱查找用户 
	* @param email
	* @return User
	* @throws
	 */
	public User findByEmail(String email) {
		try{
			String sql = "SELECT * FROM tb_user WHERE email=?";
			return  qr.query(sql, new BeanHandler<User>(User.class),email);
		
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	* @Title: add 
	* @Description: 添加用户 
	* @param form
	* @return void
	* @throws
	 */
	public void add(User form) {
		
		try {
			String sql = "insert into tb_user values(?,?,?,?,?,?)";
			Object[] params = {form.getUid(), form.getUsername(), 
					form.getPassword(), form.getEmail(), form.getCode(),
					form.isState()};
			qr.update(sql, params);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	* @Title: 通过邮箱和激活码激活账户 
	* @Description: TODO 
	* @param @param email
	* @param @param code
	* @param @return
	* @return User
	* @throws
	 */
	public User findByEmail(String email, String code) {
		
		try{
			String sql = "SELECT * FROM tb_user WHERE email=? AND code=?";
			User user = qr.query(sql, new BeanHandler<User>(User.class),email,code);
/*			String sql2 ="UPDATE tb_user SET state=1 WHERE uid=? ";
			qr.update(sql2,user.getUid());
			return user;*/
			return user;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

    public int updateState(String uid,boolean state){
    	try{
		String sql ="UPDATE tb_user SET state=? WHERE uid=? ";
		return qr.update(sql,state,uid);
    	}catch(SQLException e){
    		throw new RuntimeException(e);
    	}
    } 
	
}
