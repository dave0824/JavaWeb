package cn.dave.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dave.service.RegionService;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class CityServlet
 */
@WebServlet("/CityServlet")
public class CityServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws 
				ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		/*
		 * 1.得到id
		 * 2.調用regionService.findById方法
		 * 3.將得到的city数组转化为JSON对象
		 * 4.将JSON对象转化为字符串形式，发回jsp
		 */
		String pid = request.getParameter("id");
		int id = Integer.parseInt(pid);
		String text = JSONArray.fromObject(new RegionService().findById(id)).toString();
		System.out.println(text);
		response.getWriter().print(text);
		
	}

}
