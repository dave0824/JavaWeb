package cn.dave.bookstore.user.web.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Session;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dave.bookstore.cart.domain.Cart;
import cn.dave.bookstore.user.domain.User;
import cn.dave.bookstore.user.service.UserException;
import cn.dave.bookstore.user.service.UserService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import cn.itcast.servlet.BaseServlet;


/**
 * Servlet implementation class UserServlet
 */
/**
 * 
 * @className：UserServlet.java
 * @Title:     UserServlet
 * @Description: user 
 * @Company:   dave集团
 * @author:    dave
 * @version:   v1.0
 * @date:      2019年5月5日下午11:44:36
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {

	private UserService userService = new UserService();

	/**
	 * 
	 * @author: dave
	 * @date:   2019年5月5日 下午11:44:18
	 * @Description: 注册
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException String  
	 * @throws
	 */
	public String regist(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		/*
		 * 1.封装表单数据到User form对象中
		 * 2.补全UUID，code（激活码）
		 * 3.输入校验（不访问数据库）
		 * 	 1).保存错误信息到request域中
		 * 	 2).保存form到request域中，用于回显
		 * 	 3).转发到register.jsp
		 * 4.调用service的register方法
		 * 	 1).保存异常信息到msg到request域
		 * 	 2).保存form到request域中，用于回显
		 *   3).转发到regist.jsp
		 * 5.发邮件
		 * 6.保存成功信息到request域中
		 * 7.转发到msg.jsp
		 */
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		form.setUid(CommonUtils.uuid());
		String code = CommonUtils.uuid();//记住UUID用于发邮件校验
		form.setCode(code);
		
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
		
		//对邮箱进行校验
		String email = form.getEmail();
		if(email == null ||email.trim().isEmpty()){
			errors.put("email", "邮箱能不为空");
		}else if(!email.matches("\\w+@\\w+\\.\\w+")){
			errors.put("email","邮箱格式不对");
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
			request.setAttribute("user", form);
			
			return "f:/jsps/user/regist.jsp";

		}
		
		try {
			userService.regist(form);
		} catch (UserException e) {
			
			//获取异常信息保存到request域中
			request.setAttribute("msg",e.getMessage());
			//保存表单数据到request域中，用于回显
			request.setAttribute("user", form);
			//转发到Registe
			return "f:/jsps/user/regist.jsp";
		}
		
/*		
		 * 发邮箱
		 
		
		Properties prop = new Properties();
		prop.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));//加载邮件的配置文件
		String host = prop.getProperty("host");//获取服务器
		String emailUsername = prop.getProperty("emailUsername");//获取用户名
		String emailPassword = prop.getProperty("emailPassword");//获取密码
		String from = prop.getProperty("from");//获取发件人
		String to = form.getEmail();//获取收件人
		String subject = prop.getProperty("subject");//获取主题
		String content = prop.getProperty("content");//获取内容
		content = MessageFormat.format(content, form.getCode());//替换code
		
		Session session = MailUtils.createSession(host, emailUsername, emailPassword);//创建session
		Mail mail = new Mail(from, to, subject, content);//创建邮件
		
		try {
			MailUtils.send(session, mail);//发送
		} catch (Exception e) {
			
			e.printStackTrace();
		} */
		
		
		request.setAttribute("msg", "注册成功,请激活！");
		request.setAttribute("email", form.getEmail());
		return "f:/jsps/user/activation.jsp";
	}

	/**
	 * 
	 * @author: dave
	 * @date:   2019年5月5日 下午11:42:26
	 * @Description: 发邮件
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException String  
	 * @throws
	 */
	public String send(HttpServletRequest request,HttpServletResponse response) throws IOException{
			/*
			 * 1.通过email获取user再通过user获取code
			 * 2.通过email调用userService中的findByEmail方法比对code
			 * 3.返回user则保存成功信息到msg转发到msg.jsp
			 * 4.返回null则保存失败信息到msg转发到user/activation.jsp
			 */
			String email = request.getParameter("email");
			if(email == null ||email.trim().isEmpty()){
				request.setAttribute("msg","邮箱不能为空");
				return "f:/jsps/user/activation.jsp";
			}else if(!email.matches("\\w+@\\w+\\.\\w+")){
				request.setAttribute("msg","邮箱格式不对");
				return "f:/jsps/user/activation.jsp";
			}
			Properties prop = new Properties();
			prop.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));//加载邮件的配置文件
			String host = prop.getProperty("host");//获取服务器
			String emailUsername = prop.getProperty("emailUsername");//获取用户名
			String emailPassword = prop.getProperty("emailPassword");//获取密码
			String from = prop.getProperty("from");//获取发件人
			String to = email;//获取收件人
			String subject = prop.getProperty("subject");//获取主题
			String content = prop.getProperty("content");//获取内容
			
			User user;
			try {
				user = userService.findByEmail(email);
			} catch (UserException e1) {
				request.setAttribute("msg",e1.getMessage());
				return "f:/jsps/user/activation.jsp";
			}
			content = MessageFormat.format(content, user.getCode(),email,user.getCode());//替换code
			
			Session session = MailUtils.createSession(host, emailUsername, emailPassword);//创建session
			Mail mail = new Mail(from, to, subject, content);//创建邮件
			
			try {
				MailUtils.send(session, mail);//发送
				request.setAttribute("msg", "邮件发送成功，请点击邮件链接激活或输入激活码");
				return "f:/jsps/user/activation.jsp";
			} catch (Exception e) {
				
				request.setAttribute("email", email);
				request.setAttribute("msg", "邮件发送失败，请检查邮箱是否正确再次重试");
				return "f:/jsps/user/activation.jsp";
			}
	}
	
	/**
	 * 
	 * @author: dave
	 * @date:   2019年5月5日 下午11:31:06
	 * @Description: 邮箱激活
	 * @param request
	 * @param response
	 * @return String  
	 * @throws
	 */
	public String active(HttpServletRequest request,HttpServletResponse response){
		
		/*
		 * 邮箱激活
		 * 1.获取code,email
		 * 2.若email不为null则调用service中findByEmail方法否则调用findByCode方法
		 * 3.若返回null则保存错误信息到msg，转发到激活页面重新发送邮箱
		 * 4.若返回user则激活成功，保存成功信息到msg，转发到msg.jsp
		 */
		String code = request.getParameter("code");
		String email = request.getParameter("email");
			try {
				if(userService.findByEmail(email,code) == 1){
					request.setAttribute("msg", "恭喜您激活成功！");
					return "f:/jsps/msg.jsp";
				}else{
					request.setAttribute("msg", "激活失败，请重试");
					return "f:/jsps/user/activation.jsp";
				}
			} catch (UserException e) {
				request.setAttribute("msg",e.getMessage());
				return "f:/jsps/user/activation.jsp";
			}
	}
	
	public String login(HttpServletRequest request,HttpServletResponse response){
		
		/*
		 * 1.封装表单数据
		 * 2.调用userService的login方法
		 * 	 --保存异常信息到msg，保存表单信息用于回显
		 * 	 --转发到login.jsp
		 * 3.保存用户信息到session
		 * 4.重定向到主页
		 */
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		try{
			User user = userService.login(form);
			request.getSession().invalidate();
			request.getSession().setAttribute("session_user", user);
			/*
			 * 登录成功给一辆购物车
			 */
			request.getSession().setAttribute("cart", new Cart());
			return "r:/jsps/main.jsp";
		}catch(UserException e){
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:jsps/user/login.jsp";
		}

	}
	
	/**
	 * 
	 * @author: dave
	 * @date:   2019年5月6日 下午4:34:17
	 * @Description: 用户退出模块
	 * @param request
	 * @param response
	 * @return String  
	 * @throws
	 */
	public String quit(HttpServletRequest request,HttpServletResponse response){
		
		request.getSession().invalidate();
		return "r:/jsps/main.jsp";
	}
}
