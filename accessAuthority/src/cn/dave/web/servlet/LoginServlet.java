package cn.dave.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginSerlvet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 1. 获取用户名
		 * 2. 判断用户名中是否包含itcast
		 *   3. 如果包含，就是管理员
		 *   4. 如果不包含，就是普通会员
		 * 5. 要把登录的用户名称保存到session中
		 * 6. 转发到index.jsp
		 */
		String username = request.getParameter("username");
		if(username.contains("dave")){
			request.getSession().setAttribute("admin", username);
		}else{
			request.getSession().setAttribute("username", username);
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
