package cn.dave.bookstore.category.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dave.bookstore.category.service.CategoryService;
import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {

	CategoryService categoryService = new CategoryService();
	/**
	 * 查找所有图书分类
	 * @author: dave
	 * @date:   2019年5月6日 下午5:56:39
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/jsps/left.jsp";
	}

}
