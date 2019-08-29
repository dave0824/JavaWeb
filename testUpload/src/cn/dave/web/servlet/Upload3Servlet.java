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
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.commons.CommonUtils;

/**
 * Servlet implementation class Upload3Servlet
 */
@WebServlet("/Upload3Servlet")
public class Upload3Servlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		/*
		 * 解析三步
		 * 1.得到工厂DiskFileItemFactory factory
		 * 2.得到解析器ServletFileUpload(factory)
		 * 3.得到list<FileItem> sfu.parseRequest(request)     
		 */
		try {
				//DiskFileItemFactory factory = new DiskFileItemFactory();
				
				//设置缓存大小和缓存文件存放的位置
				DiskFileItemFactory factory = new DiskFileItemFactory(20*1024,new File("f:/temp"));
				
				ServletFileUpload sfu = new ServletFileUpload(factory); 
				//sfu.setFileSizeMax(10*1024);//设置单个文件的最大为10k
				//sfu.setSizeMax(1024*1024);//设置所有文件的总大小为1MB
				
				List<FileItem> fileItemList = sfu.parseRequest(request);
				FileItem fi = fileItemList.get(1);
				
				/*
				 * 1.得到文件保存路径
				 */
				String root = this.getServletContext().getRealPath("/WEB-INF/files/");
				
				/*
				 * 2. 生成二层目录
				 *   1). 得到文件名称
				 *   2). 得到hashCode
				 *   3). 转发成16进制
				 *   4). 获取前二个字符用来生成目录
				 */
				String fileName = fi.getName();//获取上传文件的名字
				/*
				 * 处理文件绝对路径的问题
				 */
				int index = fileName.lastIndexOf("\\");
				if(index != -1){
					fileName = fileName.substring(index+1);
				}
				/*
				 * 给文件名加上UUID前缀，处理文件同名问题
				 */
				String saveName = CommonUtils.uuid()+"_"+fileName;
				
				/*
				 * 得到hashCode
				 */
				int hCode = fileName.hashCode();
				String hex = Integer.toHexString(hCode);
				
				/*
				 * 获取hex的前两个字母,与root连接生成一个完整的路径
				 */
				File dirFile = new File(root,hex.charAt(0) + "/" + hex.charAt(1));
				
				/*
				 * 创建目录链
				 */
				dirFile.mkdirs();
				
				/*
				 * 创建目标文件
				 */
				File  dest = new File(dirFile,saveName);
				fi.write(dest);
				
				
		} catch (FileUploadException e) {
			
			if(e instanceof FileUploadBase.FileSizeLimitExceededException) {
				request.setAttribute("msg", "您上传的文件超出了10KB！");
				request.getRequestDispatcher("/form3.jsp").forward(request, response);
			}
		}catch(Exception e2){
			throw new RuntimeException(e2);
		}
	}
}
