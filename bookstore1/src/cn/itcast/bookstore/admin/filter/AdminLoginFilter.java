package cn.itcast.bookstore.admin.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.itcast.bookstore.admin.adminuser.AdminUser;

public class AdminLoginFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		HttpServletRequest req = (HttpServletRequest) request;
		AdminUser adminUser = (AdminUser) req.getSession().getAttribute("adminUser");
		if(adminUser == null) {
			request.setAttribute("msg", "您不是管理员，不能放你过去！");
			List<String> links = new ArrayList<String>();
			links.add("<a href='" + req.getContextPath() + "/adminjsps/login.jsp'>登录</a>");
			request.setAttribute("links", links);
			request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
