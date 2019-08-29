package cn.itcast.bookstore.index;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;

public class IndexServlet extends BaseServlet {
	public String top(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/jsps/top.jsp";
	}
	
	public String body(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/jsps/body.jsp";
	}
	
	public String left(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/jsps/left.jsp";
	}
}
