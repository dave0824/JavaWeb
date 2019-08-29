package cn.dave.ajax.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Servlet implementation class CityServlet
 */
@WebServlet("/CityServlet")
public class CityServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/xml;charset=utf-8");
		
		
		/*
		 * 获取省份名称，加载该省对应的<province>元素！
		 * 把元素转换成字符串发送给客户端
		 */
		/*
		 * 1. 获取省份的名称
		 * 2. 使用省份名称查找到对应的<province>元素
		 * 3. 把<province>元素转换成字符串，发送！
		 */
		try{
			/*
			 * 得到document
			 */
			SAXReader reader = new SAXReader();
			InputStream input = this.getClass().getResourceAsStream("/china.xml");
			Document doc = reader.read(input);
			
			/*
			 * 获取参数
			 */
			String pname = request.getParameter("pname");//获取省份名称
			//通过xpath查找省份名称元素
			Element proEle = (Element) doc.selectSingleNode("//province[@name='"+pname+"']");
			//将元素转换为string
			String proStr = proEle.asXML();
			response.getWriter().print(proStr);
		}catch(Exception e){
			new RuntimeException(e);
		}
	}

}
