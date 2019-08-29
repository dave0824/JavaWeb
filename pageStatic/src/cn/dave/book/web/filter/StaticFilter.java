package cn.dave.book.web.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class StaticFilter
 */
//@WebFilter(servletNames = { "BookServlet" })
@WebFilter("/BookServlet")
public class StaticFilter implements Filter {

	private FilterConfig fConfig;
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse rep=(HttpServletResponse) response;
		/*
		 * 1. 第一次访问时，查找请求对应的html页面是否存在，如果存在重定向到html
		 * 2. 如果不存在，放行！把servlet访问数据库后，输出给客户端的数据保存到一个html文件中
		 *   再重定向到html
		 */
		/*
		 * 一、获取category参数！
		 * category有四种可能：
		 * * null --> null.html
		 * * 1 --> 1.html
		 * * 2 --> 2.html
		 * * 3 --> 3.html
		 * 
		 * html页面的保存路径, htmls目录下
		 * 
		 * 判断对应的html文件是否存在，如果存在，直接重定向！
		 */
		String category = req.getParameter("category");
		//得到文件对应名
		String htmlPage = category + ".html";
		//得到文件对应路径
		String htmlPath = fConfig.getServletContext().getRealPath("/htmls");
		
		File destFile = new File(htmlPath,htmlPage);
		destFile.toString();
		
		if(destFile.exists()){
			rep.sendRedirect(req.getContextPath() + "/htmls/" +  htmlPage);
			return ;
		}
		/*
		 * 二、如果html文件不存在，我们要生成html
		 * 1. 放行，show.jsp会做出很多的输出，我们要让它别再输出给客户端，而是输出到我们指定的一个html文件中
		 * 完成：
		 * * 调包response，让它的getWriter()与一个html文件绑定，那么show.jsp的输出就到了html文件中
		 */
		StaticResponse sr = new StaticResponse(rep, destFile.getAbsolutePath());
		chain.doFilter(request, sr);//放行，即生成了html文件
		
		// 这时页面已经存在，重定向到html文件
		rep.sendRedirect(req.getContextPath() + "/htmls/" + htmlPage);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig=fConfig;
	}

}
