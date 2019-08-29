/**
 * 
 */
package com.dave.customer.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.dave.customer.domain.Customer;
import com.dave.customer.domain.PageBean;

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

		/**
		 * 
		* @Title: findAll 
		* @Description: 分页查找所有 
		* @param @param ps
		* @param @param pc
		* @param @return
		* @return PageBean<Customer>
		* @throws
		 */
		public PageBean<Customer> findAll(int ps,int pc) {
			/*
			 * 1. 他都PageBean对象pb
			 * 2. 设置pb的pc和ps
			 * 3. 得到tr，设置给pb
			 * 4. 得到beanList，设置给pb
			 * 5. 返回pageBean
			 */
			try{
					PageBean<Customer> pb = new PageBean<Customer>();
					pb.setPc(pc);
					pb.setPs(ps);
					/*
					 * 得到tr
					 */
					String sql = "SELECT COUNT(*) FROM t_customer";
					Number number = (Number) queryRunner.query(sql, new ScalarHandler());
					int tr = number.intValue();
					pb.setTr(tr);
					/*
					 * 得到BeanList
					 */
					sql="SELECT * FROM t_customer ORDER BY cname LIMIT ?,?";
					List<Customer> customerList = queryRunner.query(sql, 
							new BeanListHandler<Customer>(Customer.class),(pc-1)*ps,ps);
					pb.setBeanList(customerList);
					
					return pb;
			}
			catch(SQLException e){
				throw new RuntimeException(e);
			}
		}
		
		/**
		 * 
		* @Title: query 
		* @Description: 分页条件查找 
		* @param @param customer
		* @param @param pc
		* @param @param ps
		* @param @return
		* @return PageBean<Customer>
		* @throws
		 */
/*		public PageBean<Customer> query(Customer criteria, int pc, int ps) {
			
			 * 1. 创建PageBean对象　
			 * 2. 设置已有的属性，pc和ps
			 * 3. 得到tr
			 * 4. 得到beanList
			 
			
			 * 创建pb，设置已有属性
			 
			try{
				PageBean<Customer> pb = new PageBean<Customer>();
				pb.setPc(pc);
				pb.setPs(ps);
				
				StringBuilder selSql = new StringBuilder("SELECT COUNT(*) FROM t_customer");
				StringBuilder wheSql = new StringBuilder("WHERE 1=1");
				
				 * 2. 判断条件，完成向sql中追加where子句
				 
				
				 * 3. 创建一个ArrayList，用来装载参数值
				 
				List<Object> params = new ArrayList<Object>();
				String cname = criteria.getCname();
				if(cname != null && !cname.trim().isEmpty()) {
					wheSql.append(" and cname like ?");
					params.add("%" + cname + "%");
				}
				
				String gender = criteria.getGender();
				if(gender != null && !gender.trim().isEmpty()) {
					wheSql.append(" and gender=?");
					params.add(gender);
				}
				
				String cellphone = criteria.getCellphone();
				if(cellphone != null && !cellphone.trim().isEmpty()) {
					wheSql.append(" and cellphone like ?");
					params.add("%" + cellphone + "%");
				}
				
				String email = criteria.getEmail();
				if(email != null && !email.trim().isEmpty()) {
					wheSql.append(" and email like ?");
					params.add("%" + email + "%");
				}
				Number num = (Number)queryRunner.query(selSql.append(wheSql).toString(), 
						new ScalarHandler(), params.toArray());
				
				int tr = num.intValue();
				pb.setTr(tr);
				
				StringBuilder sql = new StringBuilder("SELECT * FROM t_customer");
				StringBuilder limitSql = new StringBuilder("LIMIT ?,?");
				
				params.add((pc-1)*ps);
				params.add(ps);
				
				List<Customer> customers = queryRunner.query(
						sql.append(wheSql).append(limitSql).toString(), 
							new BeanListHandler<Customer>(Customer.class),
								params.toArray());
				pb.setBeanList(customers);
				return pb;
			}catch(SQLException e){
				throw new RuntimeException(e);
			}
		}*/
		
		public PageBean<Customer> query(Customer criteria, int pc, int ps) {
			try {
				/*
				 * 1. 创建PageBean对象　
				 * 2. 设置已有的属性，pc和ps
				 * 3. 得到tr
				 * 4. 得到beanList
				 */
				/*
				 * 创建pb，设置已有属性
				 */
				PageBean<Customer> pb = new PageBean<Customer>();
				pb.setPc(pc);
				pb.setPs(ps);
				
				/*
				 * 得到tr
				 */
				
				/*
				 * 1. 给出一个sql语句前半部
				 */
				StringBuilder cntSql = new StringBuilder("select count(*) from t_customer");
				StringBuilder whereSql = new StringBuilder(" where 1=1");
				/*
				 * 2. 判断条件，完成向sql中追加where子句
				 */
				/*
				 * 3. 创建一个ArrayList，用来装载参数值
				 */
				List<Object> params = new ArrayList<Object>();
				String cname = criteria.getCname();
				if(cname != null && !cname.trim().isEmpty()) {
					whereSql.append(" and cname like ?");
					params.add("%" + cname + "%");
				}
				
				String gender = criteria.getGender();
				if(gender != null && !gender.trim().isEmpty()) {
					whereSql.append(" and gender=?");
					params.add(gender);
				}
				
				String cellphone = criteria.getCellphone();
				if(cellphone != null && !cellphone.trim().isEmpty()) {
					whereSql.append(" and cellphone like ?");
					params.add("%" + cellphone + "%");
				}
				
				String email = criteria.getEmail();
				if(email != null && !email.trim().isEmpty()) {
					whereSql.append(" and email like ?");
					params.add("%" + email + "%");
				}
				
				/*
				 * select count(*) .. + where子句
				 * 执行之
				 */
				Number num = (Number)queryRunner.query(cntSql.append(whereSql).toString(), 
						new ScalarHandler(), params.toArray());
				int tr = num.intValue();
				pb.setTr(tr);
				
				/*
				 * 得到beanList
				 */
				StringBuilder sql = new StringBuilder("select * from t_customer");
				// 我们查询beanList这一步，还需要给出limit子句
				StringBuilder limitSql = new StringBuilder(" limit ?,?");
				// params中需要给出limit后两个问号对应的值
				params.add((pc-1)*ps);
				params.add(ps);
				// 执行之
				List<Customer> beanList = queryRunner.query(sql.append(whereSql).append(limitSql).toString(), 
						new BeanListHandler<Customer>(Customer.class), 
						params.toArray());
				pb.setBeanList(beanList);
				
				return pb;
				
			} catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
}
