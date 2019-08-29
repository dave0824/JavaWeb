package cn.dave.bookstore.order.domain;

import java.util.ArrayList;
import java.util.List;

public class OrderList {
	private String username;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private List<Order> orderList = new ArrayList<Order>();

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	@Override
	public String toString() {
		return "OrderList [orderList=" + orderList + "]";
	} 
	
	
}
