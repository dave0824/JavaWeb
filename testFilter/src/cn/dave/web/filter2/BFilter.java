package cn.dave.web.filter2;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class BFilter
 */
@WebFilter(description = "bfilter", urlPatterns = { "/index.jsp" })
public class BFilter implements Filter {



	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("BFilter拦截。。。");
	}


	public void init(FilterConfig fConfig) throws ServletException {

	}

}
