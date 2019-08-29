package cn.dave.ajax.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BServlet
 */
@WebServlet("/BServlet")
public class BServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/xml;charset=utf-8");
		
		String students ="<students>"+
							"<student number='dave_0824'>"+
								"<name>"+"张三"+"</name>"+
								"<age>"+"18"+"</age>"+
								"<sex>"+"male"+"</sex>"+
							"</student>"+
						"</students>";
		response.getWriter().print(students);
	}



}
