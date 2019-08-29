package cn.dave.ajax.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ValidateUsernameServlet
 */
@WebServlet("/ValidateUsernameServlet")
public class ValidateUsernameServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1. 获取参数username
		 * 2. 判断是否为itcast
		 *   3. 如果是：响应1
		 *   4. 如果不是：响应0
		 */
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("text/html;charset=utf-8");
		
		String username = request.getParameter("username");
		if(username.equalsIgnoreCase("dave")){
			response.getWriter().print("1");
		}else{
			response.getWriter().print("0");
		}
	}

}
