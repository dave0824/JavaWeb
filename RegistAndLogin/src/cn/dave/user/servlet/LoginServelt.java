package cn.dave.user.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dave.user.domain.User;
import cn.dave.user.service.UserException;
import cn.dave.user.service.UserService;
import cn.itcast.commons.CommonUtils;

/**
 * Servlet implementation class LoginServelt
 */
@WebServlet(description = "loginServlet", urlPatterns = { "/LoginServlet" })
public class LoginServelt extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置响应头，解决乱码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//依赖UserService
		UserService userService = new UserService();
		//封装数据表单
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
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
		String username = form.getUsername();
		if(username == null ||username.trim().isEmpty()){
			errors.put("username", "用户名不能为空");
		}else if(username.length()<2||username.length()>15){
			errors.put("username","用户名长度必须在2到15之间");
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
		 * 1.判断Map是否为空，如果为空则说明有错误
		 * 保存errors和form到request中，后者是为了回写
		 * 转发到Regis，jsp
		 * */
		if(errors != null && errors.size()>0){
			request.setAttribute("errors", errors);
			request.setAttribute("user", form);
			RequestDispatcher rs=request.getRequestDispatcher("/user/Login.jsp");
			rs.forward(request,response);
			
			return;
		}
		
		/**
		 * 2.调用userService的regist()方法，传递form过去
		 * 3.得到异常：获取异常信息，保存到request域，转发到regist.jsp中显示
		 * 4. 没有异常：输出注册成功！
		 * */
		try{
			form = userService.login(form);
			request.getSession().setAttribute("user", form);
			request.getRequestDispatcher("/user/index.jsp").forward(request, response);

		}catch(UserException e){
			//获取异常信息保存到request域中
			request.setAttribute("msg",e.getMessage());
			//保存表单数据到request域中，用于回显
			request.setAttribute("user", form);
			//转发到Registe;
			request.getRequestDispatcher("/user/Login.jsp").forward(request, response);
			return;
		}
	}

}
