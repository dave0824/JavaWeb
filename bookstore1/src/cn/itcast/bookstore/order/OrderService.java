package cn.itcast.bookstore.order;

import java.util.List;

public class OrderService {
	private OrderDao orderDao = new OrderDao();
	
	public void deliver(String oid) {
		orderDao.updateState(oid, 3);
	}
	
	public void submit(String oid) {
		orderDao.updateState(oid, 4);
	}
	
	public void add(Order order) {
		orderDao.add(order);
	}
	
	public List<Order> findAll(String uid) {
		return orderDao.orderList(uid);
	}
	
	public List<Order> all() {
		return orderDao.all();
	}
	
	public List<Order> findByState(int state) {
		return orderDao.findByState(state);
	}
	
	public Order load(String oid) {
		return orderDao.load(oid);
	}
	
	public void updateAddress(String oid, String address) {
		orderDao.updateAddress(oid, address);
	}
	
	public void updateState(String oid, int state) {
		orderDao.updateState(oid, state);
	}
}
