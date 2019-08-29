package cn.itcast.bookstore.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.cart.Cart;
import cn.itcast.bookstore.cart.CartItem;
import cn.itcast.bookstore.user.User;
import cn.itcast.servlet.BaseServlet;
import cn.itcast.utils.CommonUtils;
import cn.itcast.utils.PaymentUtil;

public class OrderServlet extends BaseServlet {
	private OrderService orderService = new OrderService();
	
	public String pay(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String address = request.getParameter("address");
		String oid = request.getParameter("oid");// 订单编号
		orderService.updateAddress(oid, address);
		
		String p0_Cmd = "Buy";// 业务类型，固定值为buy，即“买”
		String p1_MerId = "10001126856";// 在易宝注册的商号
		String p2_Order = oid;//订单编号
		String p3_Amt = "0.01";// 支付的金额
		String p4_Cur = "CNY";// 交易种币，固定值为CNY，表示人民币
		String p5_Pid = "我去";// 商品名称
		String p6_Pcat = "";// 商品各类
		String p7_Pdesc = "";// 商品描述
		
		String p8_Url = "http://localhost:8080/bookstore1/order/OrderServlet?method=back";// 电商的返回页面，当支付成功后，易宝会重定向到这个页面
		String p9_SAF = "";// 送货地址
		String pa_MP = "";// 商品扩展信息
		String pd_FrpId = request.getParameter("pd_FrpId");// 支付通道，即选择银行
		String pr_NeedResponse = "1";// 应答机制，固定值为1

		// 密钥，由易宝提供，只有商户和易宝知道这个密钥。
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";

		// 通过上面的参数、密钥、加密算法，生成hmac值
		// 参数的顺序是必须的，如果没有值也不能给出null，而应该给出空字符串。
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue);
		
		// 把所有参数连接到网关地址后面
		String url = "https://www.yeepay.com/app-merchant-proxy/node";
		url += "?p0_Cmd=" + p0_Cmd + 
				"&p1_MerId=" + p1_MerId +
				"&p2_Order=" + p2_Order + 
				"&p3_Amt=" + p3_Amt + 
				"&p4_Cur=" + p4_Cur + 
				"&p5_Pid=" + p5_Pid + 
				"&p6_Pcat=" + p6_Pcat + 
				"&p7_Pdesc=" + p7_Pdesc + 
				"&p8_Url=" + p8_Url + 
				"&p9_SAF=" + p9_SAF + 
				"&pa_MP=" + pa_MP + 
				"&pd_FrpId=" + pd_FrpId + 
				"&pr_NeedResponse=" + pr_NeedResponse + 
				"&hmac=" + hmac;
		// 重定向到网关
		request.setAttribute("url", url);
		return "/jsps/order/send.jsp";
	}
	
	public String back(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String r1_Code = request.getParameter("r1_Code");//获取是否成功，1表示成功
		String r3_Amt = request.getParameter("r3_Amt");//获取支付的金额
		String r6_Order = request.getParameter("r6_Order");//获取订单号
		String r9_BType = request.getParameter("r9_BType");//获取响应机制，1表示重定向
		String r5_Pid = request.getParameter("r5_Pid");//商品名称
		
		orderService.updateState(r6_Order, 2);//修改订单状态为已付款
		
		if(r1_Code.equals("1")) {
			if(r9_BType.equals("1")) {
				request.setAttribute("msg", "支付成功");
				List<String> links = new ArrayList<String>();
				links.add("订单号：" + r6_Order);
				links.add("支付金额为：" + r3_Amt);
				links.add("商品名称：" + new String(r5_Pid.getBytes("ISO-8859-1"), "GBK"));
				links.add("<a href='" + request.getContextPath() + "/index.jsp'>主页</a>");
				request.setAttribute("links", links);
				return "/jsps/order/msg.jsp";
			}
		}
		return "";
	}
	
	public String orderList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			request.setAttribute("msg", "您还没有登录");
			List<String> links = new ArrayList<String>();
			links.add("<a href='" + request.getContextPath() + "/index.jsp'>主页</a>");
			links.add("<a href='" + request.getContextPath()
					+ "/jsps/login.jsp'>登录</a>");
			request.setAttribute("links", links);
			return "/jsps/msg.jsp";
		}
		List<Order> orderList = orderService.findAll(user.getUid());
		request.setAttribute("orderList", orderList);
		return "/jsps/order/orderlist.jsp";
	}
	
	public String orderDesc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		Order order = orderService.load(oid);
		request.setAttribute("order", order);
		return "/jsps/order/orderdesc.jsp";
	}
	
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart == null) {
			request.setAttribute("msg", "没有选择图书，不能生成订单！");
			return "/jsps/msg.jsp";
		}
		Collection<CartItem> cartItems = cart.getCartItems();
		if(cartItems == null || cartItems.size() == 0) {
			request.setAttribute("msg", "没有选择图书，不能生成订单！");
			return "/jsps/msg.jsp";			
		}
		
		Order order = new Order();
		order.setOid(CommonUtils.uuid());
		order.setState(1);
		order.setTotal(cart.getTotal());
		User user = (User) request.getSession().getAttribute("user");
		order.setUser(user);
		order.setOrdertime(new Date());
		String address = request.getParameter("address");
		order.setAddress(address);
		
		for(CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setItemid(CommonUtils.uuid());
			orderItem.setBook(cartItem.getBook());
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setOrder(order);
			order.addOrderItem(orderItem);
		}
		
		orderService.add(order);
		request.getSession().removeAttribute("cart");
		
		request.setAttribute("order", order);
		return "/jsps/order/orderdesc.jsp";
	}
	
	public String submit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		orderService.submit(oid);
		request.setAttribute("msg", "您已经确认收货！");
		return "/jsps/msg.jsp";
	}
}
