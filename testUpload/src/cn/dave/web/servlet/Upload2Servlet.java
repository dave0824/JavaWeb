package cn.dave.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class Upload2Servlet
 */
@WebServlet("/Upload2Servlet")
public class Upload2Servlet extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 上传三步
		 * 1. 得到工厂
		 * 2. 通过工厂创建解析器
		 * 3. 解析request，得到FileItem集合
		 * 4. 遍历FileItem集合，调用其API完成文件的保存
		 */
		try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload sfu = new ServletFileUpload(factory);
				
				List<FileItem> fileItemList;
					fileItemList = sfu.parseRequest(request);
			
				FileItem fileItem1 = fileItemList.get(0);
				FileItem fileItem2 = fileItemList.get(1);
				
				System.out.println("普通表单演示：" +fileItem1.getFieldName() +
						"=" + fileItem1.getString("UTF-8"));
				
				System.out.println("文件表单演示：");
				System.out.println("Content-Type:"+ fileItem2.getContentType());
				System.out.println("size:" + fileItem2.getSize());
				System.out.println("fileName：" + fileItem2.getName());
				
				//保存文件
				File destFile = new File("f:/dameinv.jpg");

			fileItem2.write(destFile);
		} catch (FileUploadException e) {
			
			throw new RuntimeException(e);
			
		}catch (Exception e) {
			
			throw new RuntimeException(e);
		}
	}

}
