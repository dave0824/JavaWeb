package cn.dave.bookstore.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;



public class Cart {
	private Map<String,CartItem> map = new LinkedHashMap<String, CartItem>();
	
	/**
	 * 计算总计
	 * @author: dave
	 * @date:   2019年5月7日 下午5:41:59
	 * @Description: TODO
	 * @return double  
	 * @throws
	 */
	public double getTotal() {
		// 合计=所有条目的小计之和
		BigDecimal total = new BigDecimal("0");
		for(CartItem cartItem : map.values()) {
			BigDecimal subtotal = new BigDecimal("" + cartItem.getSubtotal());
			total = total.add(subtotal);
		}
		return total.doubleValue();
	}
	/**
	 * 添加订单条目到车中
	 * @author: dave
	 * @date:   2019年5月7日 下午6:17:43
	 * @Description: TODO
	 * @param cartItem void  
	 * @throws
	 */
	public void add(CartItem cartItem){
		if(map.containsKey(cartItem.getBook().getBid())){
			CartItem _cartItem = map.get(cartItem.getBook().getBid()); 
			_cartItem.setCount(cartItem.getCount() + _cartItem.getCount());
			map.put(cartItem.getBook().getBid(), _cartItem);
		}else{
			map.put(cartItem.getBook().getBid(),cartItem);
		}
	}
	
	/**
	 * 清空购物车
	 * @author: dave
	 * @date:   2019年5月7日 下午6:18:46
	 * @Description: TODO void  
	 * @throws
	 */
	public void clear(){
		map.clear();
	}
	
	/**
	 * 删除单个条目
	 * @author: dave
	 * @date:   2019年5月7日 下午6:19:43
	 * @Description: TODO
	 * @param bid void  
	 * @throws
	 */
	public void delete(String bid){
		map.remove(bid);
	}
	
	/**
	 * 得到车中所有条目
	 * @author: dave
	 * @date:   2019年5月7日 下午6:21:07
	 * @Description: TODO
	 * @return Collection<CartItem>  
	 * @throws
	 */
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
}
