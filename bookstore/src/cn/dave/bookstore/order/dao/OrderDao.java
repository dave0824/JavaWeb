package cn.dave.bookstore.order.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import cn.dave.bookstore.book.domain.Book;
import cn.dave.bookstore.order.domain.Order;
import cn.dave.bookstore.order.domain.OrderItem;
import cn.dave.bookstore.order.domain.OrderList;
import cn.dave.bookstore.user.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class OrderDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 添加订单
	 * @author: dave
	 * @date:   2019年5月9日 下午8:15:41
	 * @Description: TODO
	 * @param order void  
	 * @throws
	 */
	public void addOrder(Order order) {
		try{
			String sql = "INSERT INTO orders VALUES(?,?,?,?,?,?)";
			//处理时间显示问题
			Timestamp timestamp = new Timestamp(order.getOrdertime().getTime());
			
			Object[] params ={order.getOid(),timestamp,order.getTotal(),
					order.getState(),order.getUser().getUid(),order.getAddress()};
			qr.update(sql,params);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * 添加条目
	 * @author: dave
	 * @date:   2019年5月9日 下午8:16:21
	 * @Description: TODO
	 * @param orderItemList void  
	 * @throws
	 */
	public void addOrderItemList(List<OrderItem> orderItemList) {
		
		/**
		 * QueryRunner类的batch(String sql, Object[][] params)
		 * 其中params是多个一维数组！
		 * 每个一维数组都与sql在一起执行一次，多个一维数组就执行多次
		 */
		try{
			String sql = "INSERT INTO orderitem VALUES(?,?,?,?,?)";
			/**
			 * 创建一个二维数组，数组的行数即为orderItemList的大小
			 */
			Object[][] params = new Object[orderItemList.size()][];
			//循环遍历orderItemList使用orderItem创建params一维数组
			for(int i=0;i<orderItemList.size();i++){
				OrderItem orderItem = orderItemList.get(i);
				params[i] = new Object[]{orderItem.getIid(),orderItem.getCount(),
						orderItem.getSubtotal(),orderItem.getOrder().getOid(),
							orderItem.getBook().getBid()};
				
			}
			qr.batch(sql, params);//执行批处理
			}catch(SQLException e){
				throw new RuntimeException(e);
			}
	}

	/**
	 * 通过uid查找我的订单
	 * @author: dave
	 * @date:   2019年5月9日 下午9:01:27
	 * @Description: TODO
	 * @param uid
	 * @return List<Order>  
	 * @throws
	 */
	public List<Order> findByUid(String uid) {
		try{
			/*
			 * 1.通过uid查找所有的order
			 */
			String sql = "SELECT * FROM orders WHERE uid=? ORDER BY ordertime";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class),uid);
			/*
			 * 2.遍历orderList,查找所有的orderItem
			 */
			for(Order order : orderList){
				loadOrderItem(order);//加载订单条目
			}
			
			return orderList;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}


	/**
	 * 加载订单条目
	 * @author: dave
	 * @date:   2019年5月9日 下午9:08:09
	 * @Description: TODO
	 * @param order void  
	 * @throws
	 */
	private void loadOrderItem(Order order) {
		try{
			String sql ="SELECT * FROM orderitem i,book b WHERE i.bid=b.bid and oid=?";
			List<Map<String,Object>>mapList = qr.query(sql, new MapListHandler(),order.getOid());
			/*
			 * mapList是多个map，每个map对应一行结果集
			 * 一行：
			 * {iid=C7AD5492F27D492189105FB50E55CBB6, count=2, subtotal=60.0, oid=1AE8A70354C947F8B81B80ADA6783155, bid=7, bname=精通Hibernate,price=30.0, author=张卫琴, image=book_img/8991366-1_l.jpg, cid=2}
			 * ...
			 * 
			 * 我们需要使用一个Map生成两个对象：OrderItem、Book，然后再建立两者的关系（把Book设置给OrderItem）
			 */
			/*
			 * 循环遍历每个Map，使用map生成两个对象，然后建立关系（最终结果一个OrderItem），把OrderItem保存起来
			 */
			List<OrderItem> orderItemList = toOrderItemList(mapList);
			order.setOrderItemList(orderItemList);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 把mapList中每个Map转换成两个对象，并建立关系
	 * @author: dave
	 * @date:   2019年5月9日 下午9:20:35
	 * @Description: TODO
	 * @param mapList
	 * @return List<OrderItem>  
	 * @throws
	 */
	private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for(Map<String,Object> map : mapList){
			OrderItem orderItem = toOrderItem(map);
			orderItemList.add(orderItem);
		}
		return orderItemList;
	}

	/**
	 * 把一个Map转换成一个OrderItem对象
	 * @author: dave
	 * @date:   2019年5月9日 下午9:25:58
	 * @Description: TODO
	 * @param map
	 * @return OrderItem  
	 * @throws
	 */
	private OrderItem toOrderItem(Map<String, Object> map) {
		OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
		Book book = CommonUtils.toBean(map, Book.class);
		orderItem.setBook(book);
		return orderItem;
	}

	/**
	 * 通过oid加载订单
	 * @author: dave
	 * @date:   2019年5月10日 上午10:20:07
	 * @Description: TODO
	 * @param oid
	 * @return Order  
	 * @throws
	 */
	public Order loadOrder(String oid) {
		try{
			/*
			 * 1.通过oid查找order
			 */
			String sql = "SELECT * FROM orders WHERE oid=?";
			Order order = qr.query(sql, new BeanHandler<Order>(Order.class),oid);
			
			loadOrderItem(order);//加载订单条目
			
			return order;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查询订单状态
	 * @author: dave
	 * @date:   2019年5月10日 上午10:41:07
	 * @Description: TODO
	 * @param oid
	 * @return int  
	 * @throws
	 */
	public int getOrderState(String oid) {
		try{
			String sql = "SELECT state FROM orders WHERE oid=?";
			Order order = qr.query(sql, new BeanHandler<Order>(Order.class),oid);
			return order.getState();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * 更新订单状态
	 * @author: dave
	 * @date:   2019年5月10日 上午10:46:07
	 * @Description: TODO
	 * @param oid
	 * @param i void  
	 * @throws
	 */
	public void updateOrderState(String oid, int state) {
		try{
			String sql = "UPDATE orders SET state=? WHERE oid=?";
			qr.update(sql,state,oid);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * 删除条目
	 * @author: dave
	 * @date:   2019年5月10日 上午11:22:32
	 * @Description: TODO
	 * @param oid void  
	 * @throws
	 */
	public void deletOrderItem(String oid) {
		try{
			String sql = "DELETE FROM orderitem WHERE oid=?";
			qr.update(sql,oid);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * 删除订单
	 * @author: dave
	 * @date:   2019年5月10日 上午11:22:18
	 * @Description: TODO
	 * @param oid void  
	 * @throws
	 */
	public void deletOrder(String oid) {
		
		try{
			String sql = "DELETE FROM orders WHERE oid=?";
			qr.update(sql,oid);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * @return 
	 * 查询所有订单
	 * @author: dave
	 * @date:   2019年5月12日 下午6:53:37
	 * @Description: TODO void  
	 * @throws
	 */
	public List<OrderList> findAll() {
		/*
		 * 按照用户排列出每个订单
		 */
		try{
			String sql = "SELECT DISTINCT uid FROM orders";
			/*
			 * 查询出用户cid，并用MAP装载
			 */
			List<Map<String, Object>> uidList = qr.query(sql, new MapListHandler());
			/*
			 * 新建List<OrderList>，遍历countList，为list的每个元素添加orderList
			 */
			List<OrderList> countList = new ArrayList<OrderList>();
			for(int i=0;i<uidList.size();i++){
				String uid = (String) uidList.get(i).get("uid");
				//调用findUid方法，得到OrderList对象
				countList.add(i, findUid(uid));
			}
			return countList;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * 通过uid查找我的订单(过载改进)
	 * @author: dave
	 * @date:   2019年5月9日 下午9:01:27
	 * @Description: TODO
	 * @param uid
	 * @return List<Order>  
	 * @throws
	 */
	public OrderList findUid(String uid) {
		try{
			/*
			 * 1.通过uid查找所有的order
			 */
			String sql = "SELECT * FROM orders WHERE uid=? ORDER BY ordertime";
			OrderList orderList = new OrderList();
			orderList.setOrderList(qr.query(sql, new BeanListHandler<Order>(Order.class),uid));
			/*
			 * 2.遍历orderList,查找所有的orderItem
			 */
			for(Order order : orderList.getOrderList()){
				loadOrderItem(order);//加载订单条目
			}
			/*
			 * 3.通过uid查找用户，并将用户名存入orderList对象中
			 */
			String sqlName = "SELECT * FROM tb_user WHERE uid=?";
			User user = new User();
			user = qr.query(sqlName, new BeanHandler<User>(User.class),uid);
			orderList.setUsername(user.getUsername());
			return orderList;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据订单转态查找订单
	 * @author: dave
	 * @date:   2019年5月13日 上午9:31:43
	 * @Description: TODO
	 * @param state
	 * @return List<OrderList>  
	 * @throws
	 */
	public List<OrderList> findOrderListByState(String state) {
		/*
		 * 按照用户排列出每个满足转态的订单
		 */
		try{
			String sql = "SELECT DISTINCT uid FROM orders WHERE state=?";
			/*
			 * 查询出用户cid，并用MAP装载
			 */
			List<Map<String, Object>> uidList = qr.query(sql, new MapListHandler(),state);
			/*
			 * 新建List<OrderList>，遍历countList，为list的每个元素添加orderList
			 */
			List<OrderList> countList = new ArrayList<OrderList>();
			for(int i=0;i<uidList.size();i++){
				String uid = (String) uidList.get(i).get("uid");
				//调用findUid方法，得到OrderList对象
				countList.add(i, findOrderByState(uid, state));
			}
			return countList;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 根据订单转态和用户名返回订单列表
	 * @author: dave
	 * @date:   2019年5月13日 上午9:53:15
	 * @Description: TODO
	 * @param uid
	 * @param state
	 * @return OrderList  
	 * @throws
	 */
	public OrderList findOrderByState(String uid,String state) {
		try{
			/*
			 * 1.通过uid查找所有的order
			 */
			String sql = "SELECT * FROM orders WHERE uid=? AND state=? ORDER BY ordertime";
			OrderList orderList = new OrderList();
			orderList.setOrderList(qr.query(sql, new BeanListHandler<Order>(Order.class),uid,state));
			/*
			 * 2.遍历orderList,查找所有的orderItem
			 */
			for(Order order : orderList.getOrderList()){
				loadOrderItem(order);//加载订单条目
			}
			/*
			 * 3.通过uid查找用户，并将用户名存入orderList对象中
			 */
			String sqlName = "SELECT * FROM tb_user WHERE uid=?";
			User user = new User();
			user = qr.query(sqlName, new BeanHandler<User>(User.class),uid);
			orderList.setUsername(user.getUsername());
			return orderList;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
