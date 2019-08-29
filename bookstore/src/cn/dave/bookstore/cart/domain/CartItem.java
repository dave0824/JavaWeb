package cn.dave.bookstore.cart.domain;

import java.math.BigDecimal;

import cn.dave.bookstore.book.domain.Book;

public class CartItem {

	private Book book;//商品
	private int count;//数量
	//private double subtotal;//小计
	
	public double getSubtotal(){
		//得到小计
		BigDecimal d1 = new BigDecimal(book.getPrice()+"");
		BigDecimal d2 = new BigDecimal(count+"");
		return d1.multiply(d2).doubleValue();
}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "Cart [book=" + book + ", count=" + count + "]";
	}
	
}
