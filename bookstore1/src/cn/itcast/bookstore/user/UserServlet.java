package cn.itcast.bookstore.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import cn.itcast.servlet.BaseServlet;
import cn.itcast.utils.CommonUtils;

public class UserServlet extends BaseServlet {
	private UserService userService = new UserService();
	
	public String quit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("cart");
		User user = (User)request.getSession().getAttribute("user");
		String msg = "";
		if(user == null) {
			msg = "您还没有登录，您就不用退出了！";
		} else {
			msg = "您已经退出，欢迎再次光临！"; 
			request.getSession().removeAttribute("user");
		}
		
		request.setAttribute("msg", msg);
		List<String> links = new ArrayList<String>();
		links.add("<a href='" + request.getContextPath() + "/index.jsp'>主页</a>");
		links.add("<a href='" + request.getContextPath()
				+ "/jsps/login.jsp'>登录</a>");
		request.setAttribute("links", links);
		return "/jsps/msg.jsp";
	}

	public String registPre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		return "/jsps/regist.jsp";
	}

	public String loginPre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		return "/jsps/login.jsp";
	}

	public String regist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 把表单数据封装到User对象中
		final User user = CommonUtils.toBean(request.getParameterMap(),
				User.class);
		user.setUid(CommonUtils.uuid());
		user.setState(false);
		user.setCode(CommonUtils.uuid() + CommonUtils.uuid());

		// 完成注册
		try {
			userService.regist(user);
		} catch (UserException e) {
			String msg = e.getMessage();// 获取异常信息
			request.setAttribute("msg", msg);
			request.setAttribute("user", user);// 是从表单来的
			return "/jsps/regist.jsp";
		}

		final String url = "http://localhost:8080/bookstore/UserServlet?method=active&code="
				+ user.getCode();

		new Thread() {
			public void run() {
				Mail mail = new Mail();
				mail.setFrom("itcast_cxf@163.com");
				mail.addToAddress(user.getEmail());
				mail.setSubject("来自ITCAST图书商城的激活邮件");
				mail.setContent("点击<a href='" + url + "'>这里</a>完成激活！");

				Session session = MailUtils.createSession("smtp.163.com",
						"itcast_cxf@163.com", "itcast");
				try {
					MailUtils.send(session, mail);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}.start();

		request.setAttribute("msg", "注册成功！请马上去邮箱激活，如果您没有马上收到邮件，说明是你的网速有问题！");
		List<String> links = new ArrayList<String>();
		links.add("<a href='" + request.getContextPath() + "/index.jsp'>主页</a>");
		links.add("<a href='" + request.getContextPath()
				+ "/jsps/login.jsp'>登录</a>");
		request.setAttribute("links", links);
		return "/jsps/msg.jsp";
	}

	public String active(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 获取激活码 查询数据库，查出来一个User对象
		 */
		String code = request.getParameter("code");
		User user = userService.findByCode(code);
		if (user == null) {
			request.setAttribute("msg", "无效的激活码！");
			List<String> links = new ArrayList<String>();
			links.add("<a href='" + request.getContextPath()
					+ "/index.jsp'>主页</a>");
			links.add("<a href='" + request.getContextPath()
					+ "/jsps/regist.jsp'>注册</a>");
			request.setAttribute("links", links);
			return "/jsps/msg.jsp";
		}
		if (user.isState()) {
			response.sendError(500, "您已经活了，再激活会死的！");
			return null;
		}

		// 修改状态
		userService.updateState(user.getUid(), true);
		request.setAttribute("msg", "恭喜！激活成功！");
		List<String> links = new ArrayList<String>();
		links.add("<a href='" + request.getContextPath() + "/index.jsp'>主页</a>");
		links.add("<a href='" + request.getContextPath()
				+ "/jsps/login.jsp'>登录</a>");
		request.setAttribute("links", links);
		return "/jsps/msg.jsp";

	}
	
	public String login(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取表单数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// 通过用户名来查看数据
		User user = userService.findByUsername(username);
		// 如果通过用户名没有查找到，说明用户不存在
		if(user == null) {
			request.setAttribute("username", username);
			request.setAttribute("msg", "用户名不存在！");
			return "/jsps/login.jsp";
		}
		
		// 如果通过用户名查询到了user对象
		// 那么比较查出来的密码与表单填写的密码是否相同
		// 如果不相同，说明密码错误
		if(!user.getPassword().equals(password)) {
			request.setAttribute("username", username);
			request.setAttribute("msg", "密码不存在！");
			return "/jsps/login.jsp";
		}
		
		// 如果用户名和密码都正确了，查看状态是否激活，如果没有激活，提示没有激活
		if(!user.isState()) {
			request.setAttribute("username", username);
			request.setAttribute("msg", "您还没有激活！");
			return "/jsps/login.jsp";
		}
		
		// 到这里说明登录成功了，把user保存到session中
		request.getSession().setAttribute("user", user);
		
		
		// 转发到msg页面，显示登录成功，并提供去往主页的超链接
		request.setAttribute("msg", "登录成功！");
		List<String> links = new ArrayList<String>();
		links.add("<a href='" + request.getContextPath() + "/index.jsp'>主页</a>");
		request.setAttribute("links", links);
		return "/jsps/msg.jsp";
	}
}
