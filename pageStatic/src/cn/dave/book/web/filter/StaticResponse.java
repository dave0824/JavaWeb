package cn.dave.book.web.filter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class StaticResponse extends HttpServletResponseWrapper {
	
	private PrintWriter pw;
	public StaticResponse(HttpServletResponse response,
			String path) throws FileNotFoundException, 
				UnsupportedEncodingException{
		
		super(response);
		
		//创建一个与HTML绑定在一起的对象流
			pw = new PrintWriter(path,"utf-8");
	}
	public PrintWriter getWriter(){
		// 返回一个与html绑定在一起的printWriter对象
		// jsp会使用它进行输出，这样数据都输出到html文件了。
		return pw;
	}
	
}
