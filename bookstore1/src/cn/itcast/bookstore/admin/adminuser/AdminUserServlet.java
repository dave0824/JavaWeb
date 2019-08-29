package cn.itcast.bookstore.admin.adminuser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;

public class AdminUserServlet extends BaseServlet {
	private AdminUserService adminUserService = new AdminUserService();
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adminname = request.getParameter("adminname");
		String password = request.getParameter("password");
		AdminUser user = adminUserService.login(adminname, password);
		if(user == null) {
			request.setAttribute("msg", "用户名或密码错误！");
			request.setAttribute("adminname", adminname);
			return "/adminjsps/login.jsp";
		}
		request.getSession().setAttribute("adminUser", user);
		response.sendRedirect(request.getContextPath() + "/adminjsps/admin/index.jsp");
		return null;
	}
}
