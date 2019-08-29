package cn.itcast.bookstore.admin.category;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.category.Category;
import cn.itcast.bookstore.category.CategoryService;
import cn.itcast.servlet.BaseServlet;
import cn.itcast.utils.CommonUtils;

public class CategoryServlet extends BaseServlet {
	private CategoryService categoryService = new CategoryService();
	
	public String all(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> list = categoryService.all();
		request.setAttribute("list", list);
		return "/adminjsps/admin/category/all.jsp";
	}
	
	public String addPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/adminjsps/admin/category/add.jsp";
	}
	
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		category.setCid(CommonUtils.uuid());
		try {
			categoryService.add(category);
		} catch(CategoryExcepiton e) {
			request.setAttribute("msg", e.getMessage());
			return "/adminjsps/admin/category/add.jsp";
		}
		request.setAttribute("msg", "添加分类成功！");
		return "/adminjsps/admin/msg.jsp";
	}
	
	public String delPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cid = request.getParameter("cid");
		Category category = categoryService.findByCid(cid);
		request.setAttribute("category", category);
		return "/adminjsps/admin/category/del.jsp";
	}
	
	public String del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cid = request.getParameter("cid");
		try {
			categoryService.del(cid);
		} catch (CategoryExcepiton e) {
			request.setAttribute("msg", e.getMessage());
			return "/adminjsps/admin/msg.jsp";
		}
		request.setAttribute("msg", "删除分类成功！");
		return "/adminjsps/admin/msg.jsp";
	}
	
	public String modPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cid = request.getParameter("cid");
		Category category = categoryService.findByCid(cid);
		request.setAttribute("category", category);
		return "/adminjsps/admin/category/mod.jsp";
	}
	
	public String mod(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		categoryService.mod(category);
		request.setAttribute("msg", "修改分类成功！");
		return "/adminjsps/admin/msg.jsp";
	}
}
