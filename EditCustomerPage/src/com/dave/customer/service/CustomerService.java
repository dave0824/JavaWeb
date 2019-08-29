package com.dave.customer.service;

import java.util.List;

import com.dave.customer.dao.CustomerDao;
import com.dave.customer.domain.Customer;
import com.dave.customer.domain.PageBean;

/**
 * 
* <p>Title: CoustomerService</p>
* <p>Description: </p>
* <p>Company: </p> 
* @author dave
* @date 2019年4月15日
 */
public class CustomerService {
	CustomerDao customerDao = new CustomerDao();
	
	/**
	 * 
	* @Title: add 
	* @Description: 添加客户 
	* @param @param customer
	* @return void 
	* @throws
	 */
	public void add(Customer customer){
		customerDao.add(customer);
	}
	/**
	 * @param i 
	 * 
	* @Title: findAll 
	* @Description: 查询所有用户
	* @param 
	* @return void
	* @throws
	 */
	public PageBean<Customer> findAll(int ps,int pc){
		return customerDao.findAll(ps,pc);
	}
	/**
	 * 
	* @Title: delete 
	* @Description: 通过cid删除客户 
	* @param @param cid
	* @param @return
	* @return int
	* @throws
	 */
	public int delete(String cid) {
		return customerDao.delete(cid);
		
	}
	/**
	* @Title: load 
	* @Description: 通过cid查找用户 
	* @param @param cid
	* @param @return
	* @return Customer
	* @throws
	 */
	public Customer load(String cid) {
		
		return customerDao.findByCid(cid);
		
	}
	public void edit(Customer customer) {
		customerDao.edit(customer);
		
	}
	public List<Customer> query(Customer customer) {
		
		return customerDao.query(customer);
		
	}
	public PageBean<Customer> query(Customer customer,int pc, int ps) {
		
		return customerDao.query(customer,pc,ps);
	}
}
