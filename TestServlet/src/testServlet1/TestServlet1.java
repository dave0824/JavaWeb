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
		System.out.println("����һ�������Լ��Է�������Ϣ������");
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		System.out.println("���ǿ�ʼ�������ڣ�ֻ�ڵ�һ�α�����ʱ����");
		
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("���Ƿ���ʱ�����ڣ�ÿ������һ�ξ�ִ��һ��");
		
	}
	
	@Override
	public void destroy() {
		System.out.println("�����������ڡ�ֻ�ڱ��ݻ�ǰ����һ��");
		
	}
}
