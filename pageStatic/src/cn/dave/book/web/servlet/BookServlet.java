package cn.dave.book.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dave.book.domain.Book;
import cn.dave.book.service.BookService;
import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends BaseServlet {
	private BookService bookService = new BookService();
  

	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Book> books = bookService.findAll();
		request.setAttribute("books", books);
		return "f:/book/show.jsp";
	}
	public String findByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String c = request.getParameter("category");
		int category = Integer.valueOf(c);
		List<Book> books = bookService.findByCategory(category);
		request.setAttribute("books", books);
		return "f:/book/show.jsp";
	}

}
