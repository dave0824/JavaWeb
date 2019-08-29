package cn.dave.bookstore.order.domain;

import cn.dave.bookstore.book.domain.Book;

/**
 * 订单条目类
 * @className：OrderItem.java
 * @Title:     OrderItem
 * @Description: TODO 
 * @Company:   dave集团
 * @author:    dave
 * @version:   v1.0
 * @date:      2019年5月9日下午7:16:01
 */
public class OrderItem {
	private String iid;//订单条目id
	private int count;//订单书本数量
	private double subtotal;//小计
	private Order order;//所属订单
	private Book book;//购买的书本
	
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	@Override
	public String toString() {
		return "OrderItem [iid=" + iid + ", count=" + count + ", subtotal=" + subtotal + ", order=" + order + ", bid="
				+ book + "]";
	}
	
	
}
