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
 * Servlet Filter implementation class Admin
 */
@WebFilter("/admin/*")
public class Admin implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 * 1. 得到session
		 * 2. 判断session域中是否存在admin，如果存在，放行,否则打回到login.jsp，并告诉它不要瞎留达
		 */
		HttpServletRequest req = (HttpServletRequest) request;
		String admin = (String) req.getSession().getAttribute("admin");
		if(admin !=null && !admin.trim().isEmpty()){
			
			chain.doFilter(request, response);
		}else{
			req.getSession().setAttribute("msg", "你特么又不是管理员，你瞎进个屁啊");
			req.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
