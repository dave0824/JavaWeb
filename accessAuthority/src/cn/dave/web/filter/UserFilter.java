package cn.dave.web.filter;

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
 * Servlet Filter implementation class UserFilter
 */
@WebFilter("/users/*")
public class UserFilter implements Filter {

	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		/*
		 * 1. 得到session
		 * 2. 判断session域中是否存在admin，如果存在，放行
		 * 3. 判断session域中是否存在username，如果存在，放行，否则打回到login.jsp，并告诉它不要瞎留达
		 */
		HttpServletRequest req = (HttpServletRequest) request; 
		String admin = (String) req.getSession().getAttribute("admin");
		String username = (String) req.getSession().getAttribute("username");
		if(admin != null && !admin.trim().isEmpty()){
			chain.doFilter(request, response);
		}
		else if(username !=null && !username.trim().isEmpty()){
			chain.doFilter(request, response);
		}else{
			req.getSession().setAttribute("msg", "你特么一个游客瞎逛什么瞎逛？");
			req.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
