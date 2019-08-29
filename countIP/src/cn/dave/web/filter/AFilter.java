package cn.dave.web.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class AFilter
 */
@WebFilter("/*")
public class AFilter implements Filter {

	FilterConfig fConfig;
	
	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		/*
		 * 1. 得到application中的map
		 * 2. 从request中获取当前客户端的ip地址
		 * 3. 查看map中是否存在这个ip对应访问次数，如果存在，把次数+1再保存回去
		 * 4. 如果不存在这个ip，那么说明是第一次访问本站，设置访问次数为1
		 */
		/*
		 * 1. 得到appliction
		 */
		ServletContext application = fConfig.getServletContext();
		@SuppressWarnings("unchecked")
		Map<String,Integer> map = (Map<String, Integer>) application.getAttribute("map");
		//得到ip
		String ip = request.getRemoteAddr();
		if(map.containsKey(ip)){
			int cnt = map.get(ip);
			map.put(ip, cnt+1);
		}else{
			map.put(ip, 1);
		}
		
		application.setAttribute("map", map);
		
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig=fConfig;
	}

}
