package cn.itcast.bookstore.order;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import cn.itcast.bookstore.user.User;

public class Order {
	private String oid;
	private double total;
	private Date ordertime;
	private int state;
	private String address;
	private User user;
	private Set<OrderItem> orderItemSet = new LinkedHashSet<OrderItem>();
	
	public void addOrderItem(OrderItem orderItem) {
		orderItemSet.add(orderItem);
	}
	
	public void removeOrderItem(OrderItem orderItem) {
		orderItemSet.remove(orderItem);
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<OrderItem> getOrderItemSet() {
		return orderItemSet;
	}

	public void setOrderItemSet(Set<OrderItem> orderItemSet) {
		this.orderItemSet = orderItemSet;
	}
}
