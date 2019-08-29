package cn.itcast.bookstore.category;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;

public class CategoryServlet extends BaseServlet {
	private CategoryService categoryService = new CategoryService();
	
	public String all(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1获取所有分类
		 * 2保存到request中
		 * 3转发到left.jsp
		 */
		List<Category> list = categoryService.all();
		request.setAttribute("list", list);
		return "/jsps/left.jsp";
	}
}
