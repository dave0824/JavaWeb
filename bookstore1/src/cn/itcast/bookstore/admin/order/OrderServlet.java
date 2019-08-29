package cn.itcast.bookstore.admin.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.order.Order;
import cn.itcast.bookstore.order.OrderService;
import cn.itcast.servlet.BaseServlet;

public class OrderServlet extends BaseServlet {
	private OrderService orderService = new OrderService();
	
	public String all(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Order> list = orderService.all();
		request.setAttribute("list", list);
		return "/adminjsps/admin/order/orderlist.jsp";
	}
	
	public String findByState(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int state = Integer.parseInt(request.getParameter("state"));
		List<Order> list = orderService.findByState(state);
		request.setAttribute("list", list);
		return "/adminjsps/admin/order/orderlist.jsp";
	}
	
	public String deliver(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		orderService.deliver(oid);
		request.setAttribute("msg", "发货成功！");
		return "/adminjsps/admin/msg.jsp";
	}
}
