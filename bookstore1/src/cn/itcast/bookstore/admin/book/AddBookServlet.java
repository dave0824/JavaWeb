package cn.itcast.bookstore.admin.book;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.bookstore.book.Book;
import cn.itcast.bookstore.book.BookService;
import cn.itcast.bookstore.category.Category;
import cn.itcast.utils.CommonUtils;

public class AddBookServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		File temp = new File(this.getServletContext().getRealPath("/book_img/temp"));
		DiskFileItemFactory factory = new DiskFileItemFactory(1024*20, temp);
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setFileSizeMax(1024*30);
		try {
			List<FileItem> list = sfu.parseRequest(request);
			Book book = new Book();
			for(FileItem fileItem : list) {
				if(fileItem.getFieldName().equals("bname")) {
					book.setBname(fileItem.getString("utf-8"));
				} else if(fileItem.getFieldName().equals("price")) {
					book.setPrice(Double.parseDouble(fileItem.getString("utf-8")));
				} else if(fileItem.getFieldName().equals("author")) {
					book.setAuthor(fileItem.getString("utf-8"));
				} else if(fileItem.getFieldName().equals("cid")) {
					Category category = new Category();
					category.setCid(fileItem.getString("utf-8"));
					book.setCategory(category);
				} else if(fileItem.getFieldName().equals("image")) {
					if(!fileItem.getContentType().startsWith("image/")) {
						request.setAttribute("msg", "您上传的不是图片");
						request.getRequestDispatcher("/adminjsps/admin/msg.jsp").forward(request, response);
						return;
					}
					String name = fileItem.getName();
					int index = name.lastIndexOf(".");
					if(index < 0) {
						request.setAttribute("msg", "上传图片的扩展名只可能是：jpg、png、bmp");
						request.getRequestDispatcher("/adminjsps/admin/msg.jsp").forward(request, response);
						return;
					}
					String ext = name.substring(index + 1);
					if(!ext.equalsIgnoreCase("jpg") && !ext.equalsIgnoreCase("png") && !ext.equalsIgnoreCase("bmp")) {
						request.setAttribute("msg", "上传图片的扩展名只可能是：jpg、png、bmp");
						request.getRequestDispatcher("/adminjsps/admin/msg.jsp").forward(request, response);
						return;
					}
					book.setBid(CommonUtils.uuid());
					name = book.getBid() + "." + ext;
					String savepath = this.getServletContext().getRealPath("/book_img");
					File file = new File(savepath, name);
					fileItem.write(file);
					fileItem.delete();
					Image image = new ImageIcon(file.getAbsolutePath()).getImage();
					if(image.getWidth(null) > 150 || image.getHeight(null) > 150) {
						request.setAttribute("msg", "上传图片的尺寸必须小于等于：150*150");
						file.delete();
						request.getRequestDispatcher("/adminjsps/admin/msg.jsp").forward(request, response);
						return;
					}
					book.setImage("book_img/" + name);
				}
			}
			BookService bookService = new BookService();
			bookService.add(book);
			request.setAttribute("msg", "添加图书成功");
			request.getRequestDispatcher("/adminjsps/admin/msg.jsp").forward(request, response);
		} catch (Exception e) {
			if(e instanceof FileUploadBase.FileSizeLimitExceededException) {
				request.setAttribute("msg", "上传图片不能大小30KB");
				request.getRequestDispatcher("/adminjsps/admin/msg.jsp").forward(request, response);
				return;
			}
			throw new ServletException(e);
		}
	}
}
