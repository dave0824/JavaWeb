package cn.dave.bookstore.book.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dave.bookstore.book.domain.Book;
import cn.dave.bookstore.book.service.BookService;
import cn.dave.bookstore.category.domain.Category;
import cn.dave.bookstore.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class AdminBookServlet
 */
@WebServlet("/admin/AdminBookServlet")
public class AdminBookServlet extends BaseServlet {
	private BookService bookService = new BookService();
	private CategoryService categoryService = new CategoryService();
	
	/**
	 * 查询所有图书
	 * @author: dave
	 * @date:   2019年5月11日 下午2:08:52
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
		return "f:/adminjsps/admin/book/list.jsp";
		
	}
	
	/**
	 * 加载图书详细内容，以便修改
	 * @author: dave
	 * @date:   2019年5月11日 下午2:13:49
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 1.查找图书存到request域中
		 * 2.调用categoryService的findAll方法查询所有的类别，存到request域中，以便修改
		 */
		request.setAttribute("book", bookService.findByBid(request.getParameter("bid")));
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/adminjsps/admin/book/desc.jsp";
	}

	/**
	 * 添加图书前
	 * 查找所有类型
	 * @author: dave
	 * @date:   2019年5月11日 下午3:12:42
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String addPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 查找所有类型保存到request域中转发到/adminjsps/admin/book/add.jsp
		 */
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/adminjsps/admin/book/add.jsp";
	}
	
	/**
	 * 删除图书
	 * @author: dave
	 * @date:   2019年5月12日 下午4:19:58
	 * @Description: 假删除，只是把del属性改为true，不显示出来而已
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 1.得到bid
		 * 2.调用service方法
		 * 3.转发到finAll()
		 */
		String bid = request.getParameter("bid");
		bookService.delete(bid);
		return findAll(request,response);
	}
	
	/**
	 * 修改图书信息
	 * @author: dave
	 * @date:   2019年5月12日 下午5:00:35
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 1.封装表单数据
		 * 2.调用service方法
		 * 3.调用findAll方法
		 */
		Book book = CommonUtils.toBean(request.getParameterMap(), Book.class);
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		book.setCategory(category);
		bookService.modify(book);
		return findAll(request, response);
	}
}
