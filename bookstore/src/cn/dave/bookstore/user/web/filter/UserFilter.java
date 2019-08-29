package cn.dave.bookstore.user.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import cn.dave.bookstore.user.domain.User;

/**
 * Servlet Filter implementation class UserFilter
 */
@WebFilter(
		urlPatterns = { 
				"/jsps/order/*", 
				"/jsps/cart/*",
				"/CartServlet", 
				"/OrderServlet"
		}
)
public class UserFilter implements Filter {

	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		User user = (User) httpRequest.getSession().getAttribute("session_user");
		if(user != null){
			chain.doFilter(request, response);
		}else{
			httpRequest.setAttribute("msg", "您还没有登录呢，请先登录！");
			httpRequest.getRequestDispatcher("/jsps/user/login.jsp")
							.forward(httpRequest, response);
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
