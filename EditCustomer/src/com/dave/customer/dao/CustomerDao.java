/**
 * 
 */
package com.dave.customer.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dave.customer.domain.Customer;

import cn.itcast.jdbc.TxQueryRunner;

/**
 * @author dave
 *
 */
public class CustomerDao {
	private QueryRunner queryRunner = new TxQueryRunner();
	/**
	 * 
	* @Title: add 
	* @Description: 添加客户
	* @param @param c
	* @return void
	* @throws
	 */
	public void add(Customer c) {
		try{
		String sql = "INSERT INTO t_customer VALUES(?,?,?,?,?,?,?)";
		Object[] parmas = { c.getCid(), c.getCname(), c.getGender(), 
				c.getBirthday(), c.getCellphone(), c.getEmail(),
				c.getDescription() };
		queryRunner.update(sql,parmas);
		}catch(SQLException e){
			throw new RuntimeException();
		}
	}
	
	/**
	 * 
	* @Title: findAll 
	* @Description: 查询所有客户
	* @param @return
	* @return List<Customer>
	* @throws
	 */
	public List<Customer> findAll() {
		try{
		String sql = "SELECT * FROM t_customer";
		return queryRunner.query(sql, new BeanListHandler<Customer>(Customer.class));
		}catch(SQLException e){
			throw new RuntimeException();
		}
	}
	
	/**
	 * 
	* @Title: delete 
	* @Description: 删除客户
	* @param @param cid
	* @param @return
	* @return int
	* @throws
	 */
	public int delete(String cid) {

		/*
		 * 1.创建sql模板
		 * 2.调用Txqr.update方法
		 */
		String sql = "DELETE FROM t_customer WHERE cid = ?";
		int i=0;
		try {
			i = queryRunner.update(sql, cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	public Customer findByCid(String cid) {
		
		/*
		 * 1.建立sql模板
		 * 2.调用TXqr.query方法
		 */
		try {
			String sql = "SELECT * FROM t_customer WHERE cid=?";
			return queryRunner.query(sql,new BeanHandler<Customer>(Customer.class),cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void edit(Customer customer)  {
		
		/*
		 * 1.建立sql模板
		 * 2.修改之
		 */
		String sql = "update t_customer set cname=?,gender=?,birthday=?," +
				"cellphone=?,email=?,description=? where cid=?";
		Object[] params = {customer.getCname(), customer.getGender(),
				customer.getBirthday(), customer.getCellphone(), customer.getEmail(),
				customer.getDescription(), customer.getCid()};
		try {
			queryRunner.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}

/*	public List<Customer> query(Customer customer) {
		*//**
		 * 1.创建sql模板
		 * 2.判断？的个数
		 * 3.执行之
		 * 4.返回结果集
		 *//*
	  try{
			List<Object> parmars = new ArrayList<Object>(); 
			StringBuilder sql = new StringBuilder("SELECT * FROM t_customer WHERE 1=1");
			if(!customer.getCname().trim().isEmpty() && customer.getCname()!=null){
				sql.append("AND Cname LIKE ?");
				parmars.add(customer.getCname());
			}
			
			if(!customer.getGender().trim().isEmpty() && customer.getGender()!=null){
				sql.append("AND gender = ?");
				parmars.add(customer.getGender());
			}
			
			if(!customer.getCellphone().trim().isEmpty() && customer.getCellphone()!=null){
				sql.append("AND CellPhone LIKE ?");
				parmars.add(customer.getCellphone());
			}
			
			if(!customer.getEmail().trim().isEmpty() && customer.getEmail()!=null){
				sql.append("AND Emaile LIKE ?");
				parmars.add(customer.getEmail());
			}
		
			return queryRunner.query(sql.toString(), 
					new BeanListHandler<Customer>(Customer.class),
					parmars.toArray());
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
	}*/
	
	public List<Customer> query(Customer criteria) {
		try {
			/*
			 * 1. 给出sql模板
			 * 2. 给出参数
			 * 3. 调用query方法，使用结果集处理器：BeanListHandler
			 */
			/*
			 * 一、　给出sql模板
			 * 二、　给出参数！
			 */
			/*
			 * 1. 给出一个sql语句前半部
			 */
			StringBuilder sql = new StringBuilder("select * from t_customer where 1=1");
			/*
			 * 2. 判断条件，完成向sql中追加where子句
			 */
			/*
			 * 3. 创建一个ArrayList，用来装载参数值
			 */
			List<Object> params = new ArrayList<Object>();
			String cname = criteria.getCname();
			if(cname != null && !cname.trim().isEmpty()) {
				sql.append(" and cname like ?");
				params.add("%" + cname + "%");
			}
			
			String gender = criteria.getGender();
			if(gender != null && !gender.trim().isEmpty()) {
				sql.append(" and gender=?");
				params.add(gender);
			}
			
			String cellphone = criteria.getCellphone();
			if(cellphone != null && !cellphone.trim().isEmpty()) {
				sql.append(" and cellphone like ?");
				params.add("%" + cellphone + "%");
			}
			
			String email = criteria.getEmail();
			if(email != null && !email.trim().isEmpty()) {
				sql.append(" and email like ?");
				params.add("%" + email + "%");
			}
			
			/*
			 * 三、执行query
			 */
			return queryRunner.query(sql.toString(), 
					new BeanListHandler<Customer>(Customer.class), 
					params.toArray());
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
