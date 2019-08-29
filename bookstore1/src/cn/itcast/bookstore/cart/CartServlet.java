package cn.itcast.bookstore.cart;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.book.Book;
import cn.itcast.bookstore.book.BookService;
import cn.itcast.servlet.BaseServlet;

public class CartServlet extends BaseServlet {
	private BookService bookService = new BookService();
	
	public String showCart(HttpServletRequest request, HttpServletResponse response) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");//从session获取Cart
		if(cart != null) {//如果车不是空的
			Collection<CartItem> cartItems = cart.getCartItems();//获取车中的图书项
			if(cartItems.size() > 0) {
				request.setAttribute("cartItems", cartItems);//保存到request中
			}
		}
		return "/jsps/cart/cartlist.jsp";//转发到cartlist.jsp遍历显示
	}
	
	public String clear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute("cart");//从session获取Cart
		if(cart != null) {//如果车不是空的
			cart.clear();
		}
		return "/jsps/cart/cartlist.jsp";
	}
	
	public String del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bid = request.getParameter("bid");
		Cart cart = (Cart) request.getSession().getAttribute("cart");//从session获取Cart
		if(cart != null) {//如果车不是空的
			cart.del(bid);
		}
		return "/cart/CartServlet?method=showCart";
	}
	
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1获取bid
		 * 2获取Book
		 * 3获取count
		 * 4CartItem(Book, count), 计算一下小计
		 * 5获取session中的Cart
		 * 6判断是否为空，如果为空，创建一个Cart
		 * 7把CartItem添加到Cart中
		 * 8把Cart放到session中
		 * ----------
		 * 要去页面显示Cart
		 */
		String bid = request.getParameter("bid");
		Book book = bookService.load(bid);
		int count = Integer.parseInt(request.getParameter("count"));
		CartItem cartItem = new CartItem();
		cartItem.setBook(book);
		cartItem.setCount(count);
		cartItem.setSubtotal(book.getPrice() * count);
		
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
		}
		cart.add(cartItem);
		request.getSession().setAttribute("cart", cart);
//		return showCart(request, response);
		return "/cart/CartServlet?method=showCart";
	}
}
