package cn.dave.web.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;

import org.apache.commons.io.IOUtils;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 两个头一个流
		 * 1.Content-Type
		 * 2.Content-Disposition
		 * 3.下载文件字节流
		 */
		String fileName = "f:/大美女.jpg";
		//为了使下载框显示的文件名不乱码,这个在我这里用microsoft测试有乱码
		//String framName = new String("大美女.jpg".getBytes("GBK"),"ISO-8859-1");
		
		//测试ok
		String framName = filenameEncoding("大美女,jpg",request);
		//通过文件名获取mime类型
		String contentType = this.getServletContext().getMimeType(fileName);
		
		String contentDisposition = "attachment;filename="+framName;
		//一个流
		FileInputStream input = new FileInputStream(fileName);
		
		//设置头
		response.setHeader("Content-Type", contentType);
		response.setHeader("Content-Disposition", contentDisposition);
		
		//获取绑定了响应端的输出流
		ServletOutputStream output = response.getOutputStream();
		//把输入流中的数据写到输出流中
		IOUtils.copy(input, output);
		//关闭之
		input.close();
		
	}
	
	//用于解决对窗口乱码问题，通用，且对特殊字符有效
	public static String filenameEncoding(String filename,HttpServletRequest request) throws UnsupportedEncodingException{
		String agent = request.getHeader("User-Agent");//获取浏览器信息
		if(agent.contains("Firefox")){
			BASE64Encoder base64Encoder = new BASE64Encoder();
			filename = "=?utf-8?B?"
					+ base64Encoder.encode(filename.getBytes("utf-8"))
					+ "?=";
		}else if(agent.contains("MSIE")) {
			filename = URLEncoder.encode(filename, "utf-8");
		} else {
			filename = URLEncoder.encode(filename, "utf-8");
		}
		return filename;
	}

}
