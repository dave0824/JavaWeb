package cn.dave.bookstore.admin.web.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dave.bookstore.admin.domain.Admin;
import cn.dave.bookstore.admin.service.AdminService;
import cn.dave.bookstore.user.service.UserException;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends BaseServlet {

	private AdminService adminService = new AdminService();
	
	/**
	 * 登录
	 * @author: dave
	 * @date:   2019年5月13日 下午12:38:23
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException String  
	 * @throws
	 */
	public String login(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		/*
		 * 1.封装表单数据
		 * 2.调用adminService的login方法
		 * 	 --保存异常信息到msg，保存表单信息用于回显
		 * 	 --转发到login.jsp
		 * 3.保存用户信息到session
		 * 4.重定向到主页
		 */
		Admin form = CommonUtils.toBean(request.getParameterMap(), Admin.class);
		System.out.println(form);
		try{
			Admin admin = adminService.login(form);
			//request.getSession().invalidate();
			request.getSession().setAttribute("session_admin", admin);

			return "r:/adminjsps/admin/index.jsp";
		}catch(UserException e){
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/adminjsps/login.jsp";
		}
	}
}
