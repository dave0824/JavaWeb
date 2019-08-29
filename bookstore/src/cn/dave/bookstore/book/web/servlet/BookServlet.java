package cn.dave.bookstore.book.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dave.bookstore.book.service.BookService;
import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends BaseServlet {

	private BookService bookService = new BookService();
	/**
	 * 查找所有图书
	 * @author: dave
	 * @date:   2019年5月6日 下午6:33:58
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("bookList", bookService.findAll());
		return "f:/jsps/book/list.jsp";
	}

	/**
	 * 按分类查找图书
	 * @author: dave
	 * @date:   2019年5月6日 下午6:37:31
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String findByCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cid = request.getParameter("cid"); 
		request.setAttribute("bookList", bookService.findByCid(cid));
		return "f:/jsps/book/list.jsp";
	}
	
	/**
	 * 通过bid查找一本图书具体内容
	 * @author: dave
	 * @date:   2019年5月6日 下午6:45:39
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String findByBid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bid =  request.getParameter("bid");
		request.setAttribute("book", bookService.findByBid(bid));
		return "f:/jsps/book/desc.jsp";
	}
}
