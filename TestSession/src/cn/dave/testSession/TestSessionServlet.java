package cn.dave.testSession;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TestSessionServlet
 */
@WebServlet("/TestSessionServlet")
public class TestSessionServlet extends HttpServlet {


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置响应头，解决乱码
		response.setCharacterEncoding("utf-8");
		
		/*
		 *1.得到vcode参数属性值
		 *2.得到验证码图片文本值
		 *3.进行比较
		 * */
		String vcode = request.getParameter("vcode");
		String session_vcode = (String) request.getSession().getAttribute("session_vcode");
		if(!vcode.equalsIgnoreCase(session_vcode)){
			
			request.setAttribute("msg", "验证码输入错误");
			RequestDispatcher rs=request.getRequestDispatcher("/session1/login.jsp");
			rs.forward(request,response);
			return;
		}
		//获取参数
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		//校验是否匹配
		if(name.equals("dave")&&password.equals("763081703")){
			//保存用户名到cookie中
			Cookie cookie = new Cookie("uname",name);
			//设置cookie的最大存活时间
			cookie.setMaxAge(60*60*24);
			response.addCookie(cookie);
			//��将用户信息存入sessionֵ
			HttpSession session=request.getSession();
			session.setAttribute("uname", name);
			//重定向
			response.sendRedirect("/TestSession/session1/succ1.jsp");
			
		}else{
			//保存错误信息到request域中，并转发到login.jsp
			request.setAttribute("msg","用户名密码错误");
			RequestDispatcher rs=request.getRequestDispatcher("/session1/login.jsp");
			rs.forward(request,response);
		}
	}

}
