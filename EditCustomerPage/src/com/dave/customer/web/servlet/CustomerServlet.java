package com.dave.customer.web.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dave.customer.domain.Customer;
import com.dave.customer.domain.PageBean;
import com.dave.customer.service.CustomerService;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet(description = "customerServlet", urlPatterns = { "/CustomerServlet" })
public class CustomerServlet extends BaseServlet {

	private CustomerService customerService = new CustomerService();
	/**
	 * 
	* @Title: add 
	* @Description: 添加用户
	* @param @param req
	* @param @param resp
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException
	* @return String
	* @throws
	 */
	public String add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * 1. 封装表单数据到Customer对象
		 * 2. 补全：cid，使用uuid
		 * 3. 使用service方法完成添加工作
		 * 4. 向request域中保存成功信息
		 * 5. 转发到msg.jsp
		 */
		Customer c = CommonUtils.toBean(req.getParameterMap(), Customer.class);
		c.setCid(CommonUtils.uuid());
		customerService.add(c);
		req.setAttribute("msg", "恭喜！添加成功");
		return "f:/msg.jsp";
		
	}
	/**
	 * 
	* @Title: findAll 
	* @Description: 查询所有用户 
	* @param @param req
	* @param @param resp
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException
	* @return String
	* @throws
	 */
/*	public String findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 * 1.保存service得到的所有客户
		 * 2.保存到request域中
		 * 3.转发到list。jsp
		 
		req.setAttribute("customerList", customerService.findAll());
		return "f:/list.jsp";
	}*/
	
	public String findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		/*
		 * 1.设置每页记录数，得到当前页码
		 * 2.调用customerService
		 * 3.保存结果集到request域中
		 * 4.转发到list.jsp
		 */
		int ps=10;
		int pc=currentPage(req);
		
		PageBean<Customer> pb = customerService.findAll(ps,pc);
		// 设置url
		pb.setUrl(getUrl(req));
		req.setAttribute("pb", pb);
		
		return "f:/list.jsp";
	}
	
	/**
	 * 
	* @Title: delete 
	* @Description: 删除客户
	* @param @param req
	* @param @param resp
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException
	* @return String
	* @throws
	 */
	public String delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * 1.获取cid
		 * 2.调用CustomerService的delete方法
		 * 3.向request域中保存删除成功信息
		 * 4.转发到msg.jsp
		 */
		String cid = req.getParameter("cid");
		if(customerService.delete(cid)!=0)
			req.setAttribute("msg","删除成功！" );
		else
			req.setAttribute("msg","删除失败！" );
		return "f:/msg.jsp";
	}
	
	/**
	 * 
	* @Title: preEdit 
	* @Description: 编辑前显示信息到edit.jsp中 
	* @param @param req
	* @param @param resp
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException
	* @return String
	* @throws
	 */
	public String preEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*
		 * 1.获得cid
		 * 2.通过cid查询客户
		 * 3.保存查询结果到request
		 * 4.转发到edit.jsp
		 */
		String cid = req.getParameter("cid");
		Customer customer = customerService.load(cid);
		req.setAttribute("customer", customer);
		
		return "f:/edit.jsp";
	}
	
	/**
	 * 
	* @Title: edit 
	* @Description: 修改客户 
	* @param @param req
	* @param @param resp
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException
	* @return String
	* @throws
	 */
	public String edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		/*
		 * 1.保存表单数据到customer对象中
		 * 2.调用customerService方法完成编辑
		 * 3.保存成功信息到request域中
		 * 4.转发到msg.jsp
		 */
		
		// 已经封装了cid到Customer对象中
		Customer customer = CommonUtils.toBean(req.getParameterMap(), Customer.class);
		customerService.edit(customer);
		req.setAttribute("msg", "修改成功！");
		return "f:/msg.jsp";
	}
	
/*	public String query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		 * 1.封装表单数据到customer对象
		 * 2.调用customerService中的query方法
		 * 3.保存查询信息到request域中
		 * 4.转发到list.jsp
		 
		Customer customer = CommonUtils.toBean(req.getParameterMap(), Customer.class);
		req.setAttribute("customerList", customerService.query(customer)); 
		return "f:/list.jsp";
	}*/
	
	public String query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/*
		 * 0. 把条件封装到Customer对象中
		 * 1. 得到pc
		 * 2. 给定ps
		 * 3. 使用pc和ps，以及条件对象，调用service方法得到PageBean
		 * 4. 把PageBean保存到request域中
		 * 5. 转发到list.jsp
		 */
		 Customer customer = CommonUtils.toBean(req.getParameterMap(), Customer.class) ;
		 
			/*
			 * 处理GET请求方式编码问题！
			 */
		 customer = encoding(customer);
		 
		 int pc = currentPage(req);
		 int ps = 10;
			
		 PageBean<Customer> pageBean = customerService.query(customer,pc,ps);
		// 得到url，保存到pb中
		 pageBean.setUrl(getUrl(req));
		 req.setAttribute("pb", pageBean);
		return "f:/list.jsp;";
	}
	private String getUrl(HttpServletRequest req) {
		String contextPath = req.getContextPath();//获取项目名
		String servletPath = req.getServletPath();//获取servletPath，即/CustomerServlet
		String queryString = req.getQueryString();//获取问号之后的参数部份
		
		//  判断参数部份中是否包含pc这个参数，如果包含，需要截取下去，不要这一部份。
		if(queryString.contains("&pc=")) {
			int index = queryString.lastIndexOf("&pc=");
			queryString = queryString.substring(0, index);
		}
		
		return contextPath + servletPath + "?" + queryString;
	}
	/**
	 * @throws UnsupportedEncodingException 
	 * 
	* @Title: encoding 
	* @Description: 修改编码格式 
	* @param @param customer
	* @param @return
	* @return Customer
	* @throws
	 */
	private Customer encoding(Customer customer) throws UnsupportedEncodingException {
		
		String cname = customer.getCname();
		String gender = customer.getGender();
		String cellphone = customer.getCellphone();
		String email = customer.getEmail();
		
		if(cname != null && !cname.trim().isEmpty()){
			cname = new String(cname.getBytes("ISO-8859-1"),"utf-8");
			customer.setCname(cname);
		}
		
/*		if(gender != null && !gender.trim().isEmpty()){
			gender = new String(gender.getBytes("ISO-8859-1"),"utf-8");
			customer.setGender(gender);
		}*/
		
		if(cellphone != null && !cellphone.trim().isEmpty()){
			cellphone = new String(cellphone.getBytes("ISO-8859-1"),"utf-8");
			customer.setCellphone(cellphone);
		}
		
		if(email != null && !email.trim().isEmpty()){
			email = new String(email.getBytes("ISO-8859-1"),"utf-8");
			customer.setEmail(email);
		}

	return customer;
}
	/**
	 * 
	* @Title: currentPage 
	* @Description: 得到当前的页码数 
	* @param @param req
	* @param @return
	* @return int
	* @throws
	 */
	private int currentPage(HttpServletRequest req) {
		/*
		 * 当前页码数如果为null则显示第一页
		 */
		String value=req.getParameter("pc");
		if(value == null || value.trim().isEmpty())
			return 1;
		else 
			return Integer.parseInt(value);
	}
}
