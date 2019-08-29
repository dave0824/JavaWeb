package cn.dave.bookstore.book.web.servlet.admin;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.dave.bookstore.book.domain.Book;
import cn.dave.bookstore.book.service.BookService;
import cn.dave.bookstore.category.domain.Category;
import cn.dave.bookstore.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;

/**
 * Servlet implementation class AdminAddBookServlet
 */
@WebServlet("/admin/AdminAddBookServlet")
public class AdminAddBookServlet extends HttpServlet {

	private CategoryService categoryService = new CategoryService();
	private BookService bookService = new BookService();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		/*
		 * 1.把表单数据封装到book对象中
		 */
		//上传三步，1.获得解析工厂
		DiskFileItemFactory factory = new DiskFileItemFactory(15 * 1024, new File("F:/temp"));
		//2.获得解析器
		ServletFileUpload sfu = new ServletFileUpload(factory);
		//限制文件大小
		sfu.setFileSizeMax(50*1024);
		//3.使用解析器去解析request对象，得到List<FileItem>
		try{
			
			List<FileItem> fileItemList = sfu.parseRequest(request);
			//创建map对象装载fileItemList数据
			Map<String,String> map = new HashMap<String,String>();
			/*
			 * * 把fileItemList中的数据封装到Book对象中
			 *   > 把所有的普通表单字段数据先封装到Map中
			 *   > 再把map中的数据封装到Book对象中
			 */
			for(FileItem fileItem : fileItemList){
				if(fileItem.isFormField()){
					map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
				}
			}
			Book book = CommonUtils.toBean(map, Book.class);
			//给book设置bid
			book.setBid(CommonUtils.uuid());
			//设置del为false
			book.setDel(false);
			/*
			 * 需要把Map中的cid封装到Category对象中，再把Category赋给Book
			 */
			Category category = CommonUtils.toBean(map, Category.class);
			book.setCategory(category);
			/*
			 * 2. 保存上传的文件
			 *   * 保存的目录
			 *   * 保存的文件名称
			 */
			// 得到保存的目录
			String savePath = this.getServletContext().getRealPath("/book_img");
			//给图片名加uuid前缀，防重名
			String name = fileItemList.get(1).getName();
			
			/*
			 * 处理文件绝对路径的问题
			 */
			int index = name.lastIndexOf("\\");
			if(index != -1){
				name = name.substring(index+1);
			}
			
			String fileName = CommonUtils.uuid() + "_" + name;
			
			/*
			 * 校验文件的扩展名
			 */
			if(!fileName.toLowerCase().endsWith("jpg")) {
				request.setAttribute("msg", "您上传的图片不是JPG扩展名！");
				request.setAttribute("categoryList", categoryService.findAll());
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp")
						.forward(request, response);
				return;
			}
			// 使用目录和文件名称创建目标文件
			File destFile = new File(savePath,fileName);
			// 保存上传文件到目标文件位置
			fileItemList.get(1).write(destFile);
			/*
			 * 3. 设置Book对象的image，即把图片的路径设置给Book的image
			 */
			book.setImage("book_img/" + fileName);
			/*
			 * 4. 使用BookService完成保存
			 */
			bookService.add(book);
			/*
			 * 校验图片的尺寸
			 */
			Image image = new ImageIcon(destFile.getAbsolutePath()).getImage();
			if(image.getWidth(null) > 200 || image.getHeight(null) > 200) {
				destFile.delete();//删除这个文件！
				request.setAttribute("msg", "您上传的图片尺寸超出了200 * 200！");
				request.setAttribute("categoryList", categoryService.findAll());
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp")
						.forward(request, response);
				return;
			}
			
			
			/*
			 * 5. 返回到图书列表
			 */
			request.getRequestDispatcher("/admin/AdminBookServlet?method=findAll")
					.forward(request, response);
		}catch(Exception e){
			if(e instanceof FileUploadBase.FileSizeLimitExceededException) {
				request.setAttribute("msg", "您上传的文件超出了15KB");
				request.setAttribute("categoryList", categoryService.findAll());
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp")
						.forward(request, response);
			}
		}
	}

}
