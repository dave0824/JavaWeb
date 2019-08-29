package cn.dave.bookstore.admin.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dave.bookstore.admin.domain.Admin;
import cn.dave.bookstore.admin.service.AdminService;
import cn.dave.bookstore.cart.domain.Cart;
import cn.dave.bookstore.user.domain.User;
import cn.dave.bookstore.user.service.UserException;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

@WebServlet("/admin/AdminServlet")
public class AdminServlet extends BaseServlet {
	
	private AdminService adminService = new AdminService();
	
	/**
	 * 管理员注册
	 * @author: dave
	 * @date:   2019年5月13日 上午11:36:41
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException String  
	 * @throws
	 */
	public String regist(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		/*
		 * 1.封装表单数据到Admin form对象中
		 * 2.补全UUID
		 * 3.输入校验（不访问数据库）
		 * 	 1).保存错误信息到request域中
		 * 	 2).保存form到request域中，用于回显
		 * 	 3).转发到register.jsp
		 * 4.调用service的register方法
		 * 	 1).保存异常信息到msg到request域
		 * 	 2).保存form到request域中，用于回显
		 *   3).转发到regist.jsp
		 * 6.保存成功信息到request域中
		 * 7.转发到msg.jsp
		 */
		Admin form = CommonUtils.toBean(request.getParameterMap(), Admin.class);
		form.setAid(CommonUtils.uuid());
		
		/**
		 * 添加新任务（表单校验）
		 * 1.创建一个新Map用来装载所有的表单错误信息
		 * 在校验的过程中如果校验失败则向Map中添加错误信息，其中Key为表单错误名称
		 * 
		 * 2.在校验之后，查看Map的长度是否为0，不是则有错误信息
		 * 保存map到request中，保存form到request中，转发到regist.jsp
		 * 
		 * 3. 如果map为空，说明没有错误信息，向下执行！
		 * 
		 * */
		//用来装载所有的错误信息
		Map<String,String> errors = new HashMap<String,String>();
		//对用户名进行校验
		String aname = form.getAname();
		if(aname == null ||aname.trim().isEmpty()){
			errors.put("aname", "用户名不能为空");
		}else if(aname.length()<2||aname.length()>15){
			errors.put("aname","用户名长度必须在2到15之间");
		}
		
		//对密码进行校验
		String password = form.getPassword();
		if(password == null ||password.trim().isEmpty()){
			errors.put("password", "密码不能为空");
		}else if(password.length()<3||password.length()>15){
			errors.put("password","密码长度必须在2到15之间");
		}
		
		/*
		 * 对验证码进行校验
		 *1.得到vcode参数属性值
		 *2.得到验证码图片文本值
		 *3.进行比较
		 * */
		String vcode = request.getParameter("vcode");
		String session_vcode = (String) request.getSession().getAttribute("session_vcode");
		if(vcode == null || vcode.trim().isEmpty()){
			errors.put("verifyCode", "验证码不能为空");
		}else if(vcode.length() !=4){
			errors.put("verifyCode","验证码长度必须为4");
		}else if(!vcode.equalsIgnoreCase(session_vcode)){
			
			errors.put("verifyCode", "验证码错误");
		}
		
		/*
		 * 1.判断Map是否为空，如果不为空则说明有错误
		 * 保存errors和form到request中，后者是为了回显
		 * 转发到Regis，jsp
		 * */
		if(errors != null && errors.size()>0){
			request.setAttribute("errors", errors);
			request.setAttribute("admin", form);
			
			return "f:/adminjsps/regist.jsp";

		}
		
		try {
			adminService.regist(form);
		} catch (UserException e) {
			
			//获取异常信息保存到request域中
			request.setAttribute("msg",e.getMessage());
			//保存表单数据到request域中，用于回显
			request.setAttribute("admin", form);
			//转发到Registe
			return "f:/adminjsps/regist.jsp";
		}
		
		request.setAttribute("msg", "注册成功,联系超管激活后登陆！");
		return "f:/adminjsps/login.jsp";
	}
	
	/**
	 * 登陆
	 * @author: dave
	 * @date:   2019年5月13日 上午11:37:22
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException String  
	 * @throws
	 */
	/*public String login(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		
		 * 1.封装表单数据
		 * 2.调用adminService的login方法
		 * 	 --保存异常信息到msg，保存表单信息用于回显
		 * 	 --转发到login.jsp
		 * 3.保存用户信息到session
		 * 4.重定向到主页
		 
		Admin form = CommonUtils.toBean(request.getParameterMap(), Admin.class);
		System.out.println(form);
		try{
			Admin admin = adminService.login(form);
			//request.getSession().invalidate();
			request.getSession().setAttribute("session_admin", admin);
			System.out.println(admin);

			return "r:/adminjsps/admin/index.jsp";
		}catch(UserException e){
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/adminjsps/login.jsp";
		}
	}*/
	
	/**
	 * 退出
	 * @author: dave
	 * @date:   2019年5月13日 上午11:57:56
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException String  
	 * @throws
	 */
	public String quit(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		//清除session，转发到login页面
		request.getSession().invalidate();
		return "f:/adminjsps/login.jsp";
	}
}
