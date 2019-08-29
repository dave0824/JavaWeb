package cn.dave.bookstore.order.service;

import java.sql.SQLException;
import java.util.List;

import cn.dave.bookstore.order.dao.OrderDao;
import cn.dave.bookstore.order.domain.Order;
import cn.dave.bookstore.order.domain.OrderList;
import cn.itcast.jdbc.JdbcUtils;

public class OrderService {
	private OrderDao orderDao = new OrderDao();

	/**
	 * 添加订单
	 * @author: dave
	 * @date:   2019年5月9日 下午7:57:31
	 * @Description: 需要使用事务，以保持数据的一致性
	 * @param order void  
	 * @throws
	 */
	public void add(Order order) {
		try{
			//开启事务
			JdbcUtils.beginTransaction();
			
			orderDao.addOrder(order);//插入订单
			orderDao.addOrderItemList(order.getOrderItemList());//插入条目
			
			//提交事务
			JdbcUtils.commitTransaction();
		}catch(Exception e){
			try{
				//回滚事务
				JdbcUtils.rollbackTransaction();
			}catch(SQLException e2){}
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * 查寻我的订单
	 * @author: dave
	 * @date:   2019年5月9日 下午8:59:57
	 * @Description: TODO
	 * @param uid
	 * @return List<Order>  
	 * @throws
	 */
	public List<Order> myOrders(String uid) {
		
		return orderDao.findByUid(uid);
	}

	/**
	 * 通过oid加载订单
	 * @author: dave
	 * @date:   2019年5月10日 上午10:19:22
	 * @Description: TODO
	 * @param oid
	 * @return Order  
	 * @throws
	 */
	public Order loadOrder(String oid) {
		
		return orderDao.loadOrder(oid);
	}

	/**
	 * @throws OrderException 
	 * 确认收货
	 * @author: dave
	 * @date:   2019年5月10日 上午10:34:06
	 * @Description: TODO
	 * @param oid void  
	 * @throws
	 */
	public void confirm(String oid) throws OrderException {
		/*
		 * 1.根据oid调用dao方法判断state是否为3，如果不是抛异常
		 * 2.调用dao方法，修改状态
		 */
		 int state = orderDao.getOrderState(oid);
		 if(state != 3)throw new OrderException("确认收货失败，确认过眼神，你是想走漏洞的人！");
		 orderDao.updateOrderState(oid,4);
	}

	/**
	 * @throws OrderException 
	 * 删除订单
	 * @author: dave
	 * @date:   2019年5月10日 上午11:05:03
	 * @Description: TODO
	 * @param oid void  
	 * @throws
	 */
	public void deleteOrder(String oid) throws OrderException {
		/*
		 * 1.调用dao方法查询订单，若不存在，抛异常
		 * 2.查询订单状态，若不等于1或4，抛异常
		 * 3.采用事务删除order，orderItem
		 */
		 Order order = orderDao.loadOrder(oid);
		 if(order == null)throw new OrderException("订单不存在，确认过眼神，你是想走漏洞的人！");
		 
		 if(order.getState()!=4)throw new OrderException("删除订单失败，确认过眼神，你是想走漏洞的人！");
		 try{
			 //开启事务
			 JdbcUtils.beginTransaction();
			 /*
			  * 应先删除条目，因为条目的外键来自于订单的编号
			  */
			 orderDao.deletOrderItem(oid);
			 orderDao.deletOrder(oid);
			 
			 //提交事务
			 JdbcUtils.commitTransaction();
		 }catch(Exception e){
			 //回滚事务
			 try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			 throw new RuntimeException(e);
		 }
	}

	/**
	 * 支付方法
	 * @author: dave
	 * @date:   2019年5月10日 下午6:14:37
	 * @Description: TODO
	 * @param r6_Order void  
	 * @throws
	 */
	public void zhiFu(String oid) {
		/*
		 * 1. 获取订单的状态
		 *   * 如果状态为1，那么执行下面代码
		 *   * 如果状态不为1，那么本方法什么都不做
		 */
		int state = orderDao.getOrderState(oid);
		if(state == 1) {
			// 修改订单状态为2
			orderDao.updateOrderState(oid, 2);
		}
		
	}

	/**
	 * 查询所有订单
	 * @author: dave
	 * @date:   2019年5月12日 下午6:52:29
	 * @Description: TODO void  
	 * @throws
	 */
	public List<OrderList> findAll() {
		return orderDao.findAll();
		
	}

	/**
	 * 发货
	 * @author: dave
	 * @date:   2019年5月12日 下午9:50:22
	 * @Description: TODO
	 * @param oid
	 * @throws OrderException void  
	 * @throws
	 */
	public void delivery(String oid) throws OrderException {
		/*
		 * 1.根据oid调用dao方法判断state是否为3，如果不是抛异常
		 * 2.调用dao方法，修改状态
		 */
		 int state = orderDao.getOrderState(oid);
		 if(state != 2)throw new OrderException("买家还未付款，别急着发货呀！");
		 orderDao.updateOrderState(oid,3);
		
	}

	public List<OrderList> findOrderByState(String state) {
		
		return orderDao.findOrderListByState(state);
	}
}
