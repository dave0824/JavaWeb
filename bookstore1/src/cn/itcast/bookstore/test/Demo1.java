package cn.itcast.bookstore.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import cn.itcast.bookstore.book.Book;
import cn.itcast.bookstore.book.BookService;
import cn.itcast.bookstore.order.Order;
import cn.itcast.bookstore.order.OrderDao;
import cn.itcast.bookstore.order.OrderItem;
import cn.itcast.bookstore.order.OrderService;
import cn.itcast.bookstore.user.User;

public class Demo1 {
	@Test
	public void fun1() {
		BookService bookService = new BookService();
		Book book1 = bookService.load("b1");
		Book book2 = bookService.load("b2");
		
		Order order = new Order();
		order.setOid("o1");
		order.setState(1);
		order.setOrdertime(new Date());
		order.setTotal(100);
		User user = new User();
		user.setUid("a9ef3cd19c22468c8fb8c4d296d5cff3");
		order.setUser(user);
		OrderItem item1 = new OrderItem();
		item1.setItemid("item1");
		item1.setBook(book1);
		item1.setCount(2);
		item1.setSubtotal(300);
		item1.setOrder(order);
		
		OrderItem item2 = new OrderItem();
		item2.setItemid("item2");
		item2.setBook(book2);
		item2.setCount(3);
		item2.setSubtotal(500);
		item2.setOrder(order);
		
		order.addOrderItem(item1);
		order.addOrderItem(item2);
		
		OrderDao dao = new OrderDao();
		
		dao.add(order);
	}
	
	@Test
	public void fun2() {
		OrderService orderService = new OrderService();
		List<Order> orderList = orderService.findAll("a9ef3cd19c22468c8fb8c4d296d5cff3");
		
		for(Order order : orderList) {
			System.out.println(order.getState() + ", " + order.getTotal());
			for(OrderItem orderItem : order.getOrderItemSet()) {
				System.out.println(orderItem.getBook());
			}
			System.out.println();
		}
	}
	
	@Test
	public void fun3() throws UnsupportedEncodingException {
		String s = "忘情水";
//		s = new String(s.getBytes(), "gbk");
//		System.out.println(s);
		System.out.println(URLEncoder.encode(s, "gbk"));
	}
}
