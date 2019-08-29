package cn.dave.bookstore.order.domain;

import java.util.Date;
import java.util.List;

import cn.dave.bookstore.user.domain.User;

/**
 * 订单类
 * @className：Order.java
 * @Title:     Order
 * @Description: TODO 
 * @Company:   dave集团
 * @author:    dave
 * @version:   v1.0
 * @date:      2019年5月9日下午7:12:38
 */
public class Order {
	private String oid;//订单id
	private Date ordertime;//订单时间
	private double total;//订单金额总额
	private int state;//订单状态：1：未付款，2：付款为发货，3：发货为接收，4：完成交易
	private User user;//用户
	private String address;//收货地址
	
	private List<OrderItem> orderItemList;//当前订单的所有条目

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", ordertime=" + ordertime + ", total=" + total + ", state=" + state + ", user="
				+ user + ", address=" + address + ", orderItemList=" + orderItemList + "]";
	}
	
    
	
}
