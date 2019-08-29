package cn.dave.bookstore.order.web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dave.bookstore.cart.domain.Cart;
import cn.dave.bookstore.cart.domain.CartItem;
import cn.dave.bookstore.order.domain.Order;
import cn.dave.bookstore.order.domain.OrderItem;
import cn.dave.bookstore.order.service.OrderException;
import cn.dave.bookstore.order.service.OrderService;
import cn.dave.bookstore.user.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
	private OrderService orderService = new OrderService();

	/**
	 * 添加订单到数据库中
	 * @author: dave
	 * @date:   2019年5月9日 下午7:55:35
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 1.获取session中的购物车
		 * 2.使用cart生成order对象
		 * 3.调用service方法
		 * 4.转发到jsps/order/desc.jsp
		 */
		/*
		 * 1.获取cart
		 */
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		/*
		 * 2.使用cart生成order
		 */
		Order order = new Order();
		order.setOid(CommonUtils.uuid());
		order.setOrdertime(new Date());
		order.setState(1);
		order.setTotal(cart.getTotal());
		/*
		 * 获取user设置给order对象
		 */
		User user = (User)request.getSession().getAttribute("session_user");
		order.setUser(user);
		/*
		 * 使用cart中的cartItems生成条目数组，
		 */
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for(CartItem cartItem : cart.getCartItems()){
			
			OrderItem orderItem = new OrderItem();
			orderItem.setBook(cartItem.getBook());
			orderItem.setCount(cartItem.getCount());
			orderItem.setIid(CommonUtils.uuid());
			orderItem.setOrder(order);
			orderItem.setSubtotal(cartItem.getSubtotal());
			
			orderItemList.add(orderItem);//将orderItem添加到orderItemList中
		}
		order.setOrderItemList(orderItemList);//将orderItemList添加到order对象中
		
		/*
		 * 调用service方法
		 */
		orderService.add(order);
		/*
		 * 清空购物车
		 */
		cart.clear();
		/*
		 * 添加order到request域中
		 */
		request.setAttribute("order", order);
		return "f:jsps/order/desc.jsp";
	}
	
	
	/**
	 * 查询所有订单
	 * @author: dave
	 * @date:   2019年5月9日 下午8:55:45
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		/*
		 * 1.从session中获取user
		 * 2.通过uid查找List<order>
		 * 3.调用service方法
		 * 4.保存List<order>到request域中
		 */
		User user = (User)request.getSession().getAttribute("session_user");
		request.setAttribute("orderList", orderService.myOrders(user.getUid()));
		return "f:/jsps/order/list.jsp";
	}
	
	/**
	 * 加载订单
	 * @author: dave
	 * @date:   2019年5月10日 上午10:15:31
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String loadOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		/*
		 * 1.获取oid
		 * 2.调用service方法
		 * 3.将order存入request域中
		 * 4.转发到f:/jsps/order/desc.jsp
		 */
		request.setAttribute("order",orderService.loadOrder(request.getParameter("oid")));
		return "f:/jsps/order/desc.jsp";
	}
	
	/**
	 * 确认收货
	 * @author: dave
	 * @date:   2019年5月10日 上午10:25:07
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		/*
		 * 1.获取oid
		 * 2.调用service方法
		 * 3.如果有异常则保存异常信息到msg，否者保存成功信息到msg
		 * 4.转发到jsps/order/msg.jsp
		 */
		String oid = request.getParameter("oid");
		try{
				orderService.confirm(oid);
				request.setAttribute("msg", "确认成功！");
		}catch(OrderException e){
			request.setAttribute("msg", e.getMessage());
		}
		
		return "f:/jsps/order/msg.jsp";
	}
	
	/**
	 * 删除订单
	 * @author: dave
	 * @date:   2019年5月10日 上午11:00:27
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
			request.setAttribute("msg","删除成功!期待您再次下单");
		} catch (OrderException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/jsps/order/msg.jsp";
	}
	
	/**
	 * 支付之去银行
	 * @author: dave
	 * @date:   2019年5月10日 下午6:06:31
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Properties props = new Properties();
		InputStream input = this.getClass().getClassLoader()
				.getResourceAsStream("merchantInfo.properties");
		props.load(input);
		/*
		 * 准备13参数
		 */
		String p0_Cmd = "Buy";
		String p1_MerId = props.getProperty("p1_MerId");
		String p2_Order = request.getParameter("oid");
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		String p8_Url = props.getProperty("p8_Url");
		String p9_SAF = "";
		String pa_MP = "";
		String pd_FrpId = request.getParameter("pd_FrpId");
		String pr_NeedResponse = "1";

		/*
		 * 计算hmac
		 */
		String keyValue = props.getProperty("keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue);

		/*
		 * 连接易宝的网址和13+1个参数
		 */
		StringBuilder url = new StringBuilder(props.getProperty("url"));
		url.append("?p0_Cmd=").append(p0_Cmd);
		url.append("&p1_MerId=").append(p1_MerId);
		url.append("&p2_Order=").append(p2_Order);
		url.append("&p3_Amt=").append(p3_Amt);
		url.append("&p4_Cur=").append(p4_Cur);
		url.append("&p5_Pid=").append(p5_Pid);
		url.append("&p6_Pcat=").append(p6_Pcat);
		url.append("&p7_Pdesc=").append(p7_Pdesc);
		url.append("&p8_Url=").append(p8_Url);
		url.append("&p9_SAF=").append(p9_SAF);
		url.append("&pa_MP=").append(pa_MP);
		url.append("&pd_FrpId=").append(pd_FrpId);
		url.append("&pr_NeedResponse=").append(pr_NeedResponse);
		url.append("&hmac=").append(hmac);

		System.out.println(url);

		/*
		 * 重定向到易宝
		 */
		response.sendRedirect(url.toString());

		return null;
		
	}
	

	/**
	 * 银行回调
	 * @author: dave
	 * @date:   2019年5月10日 下午6:09:37
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException String  
	 * @throws
	 */
	public String back(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1. 获取11 + 1
		 */
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd = request.getParameter("r0_Cmd");
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		String r6_Order = request.getParameter("r6_Order");
		String r7_Uid = request.getParameter("r7_Uid");
		String r8_MP = request.getParameter("r8_MP");
		String r9_BType = request.getParameter("r9_BType");

		String hmac = request.getParameter("hmac");

		/*
		 * 2. 校验访问者是否为易宝！
		 */
		Properties props = new Properties();
		InputStream input = this.getClass().getClassLoader()
				.getResourceAsStream("merchantInfo.properties");
		props.load(input);
		String keyValue = props.getProperty("keyValue");

		boolean bool = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
				r8_MP, r9_BType, keyValue);
		
		if(!bool) {//如果校验失败
			request.setAttribute("msg", "您再尝试找漏洞，将会收到我公司律师函，望君自重！");
			return "f:/jsps/msg.jsp";
		}
		
		/*
		 * 3. 获取状态订单，确定是否要修改订单状态，以及添加积分等业务操作
		 */
		orderService.zhiFu(r6_Order);//有可能对数据库进行操作，也可能不操作！
		
		/*
		 * 4. 判断当前回调方式
		 *   如果为点对点，需要回馈以success开头的字符串
		 */
		if(r9_BType.equals("2")) {
			response.getWriter().print("success");
		}
		
		/*
		 * 5. 保存成功信息，转发到msg.jsp
		 */
		request.setAttribute("msg", "支付成功，等待卖家发货！");
		return "f:/jsps/msg.jsp";
	}
}
