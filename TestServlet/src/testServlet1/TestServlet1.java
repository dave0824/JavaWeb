package testServlet1;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class TestServlet1 implements Servlet {



	@Override
	public ServletConfig getServletConfig() {
		
		return null;
	}

	@Override
	public String getServletInfo() {
		System.out.println("这是一个我们自己对服务器信息的描述");
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		System.out.println("这是开始生命周期，只在第一次被创建时访问");
		
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("这是访问时间周期，每被访问一次就执行一次");
		
	}
	
	@Override
	public void destroy() {
		System.out.println("结束生命周期。只在被摧毁前调用一次");
		
	}
}
