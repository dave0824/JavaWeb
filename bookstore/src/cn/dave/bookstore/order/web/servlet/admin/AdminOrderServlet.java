package cn.dave.bookstore.order.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dave.bookstore.order.service.OrderException;
import cn.dave.bookstore.order.service.OrderService;
import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class AdminOrderServlet
 */
@WebServlet("/admin/AdminOrderServlet")
public class AdminOrderServlet extends BaseServlet {

	private OrderService orderService = new OrderService();
	/**
	 * 查询所有订单
	 * @author: dave
	 * @date:   2019年5月12日 下午6:47:57
	 * @Description: TODO
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException void  
	 * @throws
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("countList", orderService.findAll());
		return "f:/adminjsps/admin/order/list.jsp";
	}

	/**
	 * 发货
	 * @author: dave
	 * @date:   2019年5月12日 下午9:43:14
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String delivery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 1.获取oid
		 * 2.调用service方法
		 * 3.存入成功信息到request域中
		 * 4.转发到adminjsps/msg.jsp
		 */
		String oid = request.getParameter("oid");
		try {
			orderService.delivery(oid);
			request.setAttribute("msg", "发货成功");
		} catch (OrderException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/adminjsps/msg.jsp";
	}
	
	/**
	 * 删除订单
	 * @author: dave
	 * @date:   2019年5月12日 下午10:19:43
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 1.获取oid
		 * 2.调用service方法
		 * 3.删除成功添加成功信息
		 * 4.删除失败保存错误信息
		 * 5.转发到msg.jsp
		 */
		String oid = request.getParameter("oid");
		try {
			orderService.deleteOrder(oid);
			request.setAttribute("msg","删除成功!");
		} catch (OrderException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/jsps/order/msg.jsp";
	}
	
	/**
	 * 根据订单转态查询订单
	 * @author: dave
	 * @date:   2019年5月13日 上午9:27:42
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String findOrderByState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取state
		 * 2.调用service方法
		 * 3.存储返回信息到request域中
		 * 4.转发
		 */
		String state = request.getParameter("state");
		request.setAttribute("countList", orderService.findOrderByState(state));
		return "f:/adminjsps/admin/order/list.jsp";
	}
}
