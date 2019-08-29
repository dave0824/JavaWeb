package cn.dave.bookstore.category.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dave.bookstore.category.domain.Category;
import cn.dave.bookstore.category.service.CategoryException;
import cn.dave.bookstore.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class AdminCategoryServlet
 */
@WebServlet("/admin/AdminCategoryServlet")
public class AdminCategoryServlet extends BaseServlet {

	private CategoryService categoryService = new CategoryService();
	
	/**
	 * 查询所有分类
	 * @author: dave
	 * @date:   2019年5月10日 下午9:50:59
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 1.调用service方法得到List<Category>
		 * 2.保存信息到request域中
		 * 3.转发到adminjsps/admin/category/list.jsp
		 */
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/adminjsps/admin/category/list.jsp";
	}
	
	/**
	 * 添加分类
	 * @author: dave
	 * @date:   2019年5月10日 下午10:04:45
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
		 * 1.封装Category对象
		 * 2.补全cid
		 * 3.调用service方法
		 * 4.有异常则捕捉异常，添加异常信息到msg，转发到adminjsps/msg.jsp
		 * 5.无异常则调用findAll方法显示
		 */
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		category.setCid(CommonUtils.uuid());
		try{
			categoryService.add(category);
			return findAll(request, response);
		}catch(CategoryException e){
			request.setAttribute("msg", e.getMessage());
			return "f:/adminjsps/msg.jsp";
		}
	}
	
	/**
	 * 修改前
	 * @author: dave
	 * @date:   2019年5月10日 下午10:33:21
	 * @Description: 根据cid查询分类用于回显
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String editPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/**
		 * 1.得到cid
		 * 2.调用service方法得到category
		 * 3.保存category到request域中
		 * 4.转发到/adminjsps/admin/category/mod.jsp
		 */
		request.setAttribute("category", categoryService.findByCid(request.getParameter("cid")));
		return "f:/adminjsps/admin/category/mod.jsp";
	}
	
	/**
	 * 修改类别名称
	 * @author: dave
	 * @date:   2019年5月10日 下午10:43:54
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 1.封装category
		 * 2.调用service方法
		 * 	  有异常则保存异常信息到msg，转发到msg.jsp
		 * 3.调用findAll方法
		 * 	 
		 */
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		try{
			categoryService.edit(category);
			return findAll(request, response);
		}catch(CategoryException e){
			request.setAttribute("msg", e.getMessage());
			return "f:/adminjsps/msg.jsp";
		}
	}
	
	/**
	 * 删除分类
	 * @author: dave
	 * @date:   2019年5月10日 下午10:58:32
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 1.获取cid
		 * 2.调用service方法
		 * 	  有异常则保存异常信息到msg，转发到msg.jsp
		 * 3.调用findAll方法
		 * 	 
		 */
		String cid = request.getParameter("cid");
		try{
			categoryService.delete(cid);
			return findAll(request, response);
		}catch(CategoryException e){
			request.setAttribute("msg", e.getMessage());
			return "f:/adminjsps/msg.jsp";
		}
	}
}
