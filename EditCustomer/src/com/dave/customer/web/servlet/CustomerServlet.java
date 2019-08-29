package com.dave.customer.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dave.customer.domain.Customer;
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
	public String findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * 1.保存service得到的所有客户
		 * 2.保存到request域中
		 * 3.转发到list。jsp
		 */
		req.setAttribute("customerList", customerService.findAll());
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
	
	public String query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*
		 * 1.封装表单数据到customer对象
		 * 2.调用customerService中的query方法
		 * 3.保存查询信息到request域中
		 * 4.转发到list.jsp
		 */
		Customer customer = CommonUtils.toBean(req.getParameterMap(), Customer.class);
		req.setAttribute("customerList", customerService.query(customer)); 
		return "f:/list.jsp";
	}
}
