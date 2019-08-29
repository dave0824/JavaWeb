package cn.itcast.bookstore.admin.book;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.book.Book;
import cn.itcast.bookstore.book.BookService;
import cn.itcast.bookstore.category.Category;
import cn.itcast.bookstore.category.CategoryService;
import cn.itcast.servlet.BaseServlet;
import cn.itcast.utils.CommonUtils;

public class BookServlet extends BaseServlet {
	private BookService bookService = new BookService();
	private CategoryService categoryService = new CategoryService();
	public String all(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Book> list = bookService.all();
		request.setAttribute("list", list);
		return "/adminjsps/admin/book/all.jsp";
	}
	
	public String addPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> categoryList = categoryService.all();
		request.setAttribute("categoryList", categoryList);
		return "/adminjsps/admin/book/add.jsp";
	}
	
	public String desc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bid = request.getParameter("bid");
		Book book = bookService.load(bid);
		request.setAttribute("book", book);
		List<Category> categoryList = categoryService.all();
		request.setAttribute("categoryList", categoryList);

		return "/adminjsps/admin/book/desc.jsp";
	}
	
	public String mod(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Book book = CommonUtils.toBean(request.getParameterMap(), Book.class);
		String cid = request.getParameter("cid");
		Category category = new Category();
		category.setCid(cid);
		book.setCategory(category);
		bookService.mod(book);
		request.setAttribute("msg", "修改图书成功");
		return "/adminjsps/admin/msg.jsp";
	}
	
	public String del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bid = request.getParameter("bid");
		bookService.del(bid);
		request.setAttribute("msg", "删除图书成功");
		return "/adminjsps/admin/msg.jsp";
	}
}
