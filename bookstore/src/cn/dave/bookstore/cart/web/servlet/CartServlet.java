package cn.dave.bookstore.cart.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dave.bookstore.book.domain.Book;
import cn.dave.bookstore.book.service.BookService;
import cn.dave.bookstore.cart.domain.Cart;
import cn.dave.bookstore.cart.domain.CartItem;
import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {

	/**
	 * 添加条目到车中
	 * @author: dave
	 * @date:   2019年5月7日 下午6:24:02
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 1. 得到车
		 * 2. 得到条目（得到图书和数量）
		 * 3. 把条目添加到车中
		 */
		/*
		 * 1. 得到车
		 */
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		String bid = request.getParameter("bid");
		int count = Integer.parseInt(request.getParameter("count"));
		/*
		 * 2.获得书
		 */
		Book book = new BookService().findByBid(bid);
		/*
		 * 创建条目
		 */
		CartItem cartItem = new CartItem();
		cartItem.setBook(book);
		cartItem.setCount(count);
		/*
		 * 把条目添加到车中
		 */
		cart.add(cartItem);
		
		return "f:/jsps/cart/list.jsp";
	}
	
	/**
	 * 清空车
	 * @author: dave
	 * @date:   2019年5月7日 下午6:41:59
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		cart.clear();
		return "f:/jsps/cart/list.jsp";
	}

	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		String bid = request.getParameter("bid");
		cart.delete(bid);
		return "f:/jsps/cart/list.jsp";
	}
}
