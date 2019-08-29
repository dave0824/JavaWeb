package cn.dave.bookstore.admin.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter({ "/AdminFilter", "/admin/*" ,"/adminjsps/admin/*"})
public class AdminFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if(httpRequest.getSession().getAttribute("session_admin") != null){
			chain.doFilter(request, response);
		}else{
			httpRequest.setAttribute("msg", "管理员先生，您还没有登录呢，请您先登录！");
			httpRequest.getRequestDispatcher("/adminjsps/login.jsp").forward(httpRequest, response);
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
