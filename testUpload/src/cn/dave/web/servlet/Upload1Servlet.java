package cn.dave.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

/**
 * Servlet implementation class Upload1Servlet
 */
@WebServlet("/Upload1Servlet")
public class Upload1Servlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("tesxt/html;charset=utf-8");
		
		ServletInputStream in = request.getInputStream();
		String s = IOUtils.toString(in);
		System.out.println(s);
	}

}
