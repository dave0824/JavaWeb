package cn.itcast.bookstore.cart;

import cn.itcast.bookstore.book.Book;

public class CartItem {
	private Book book;
	private int count;
	private double subtotal;
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
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	@Override
	public String toString() {
		return "CartItem [count=" + count + ", subtotal=" + subtotal + "]";
	}
	
	
}
