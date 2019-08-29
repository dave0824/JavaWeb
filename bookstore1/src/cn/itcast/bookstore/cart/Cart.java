package cn.itcast.bookstore.cart;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	private Map<String,CartItem> map = new LinkedHashMap<String,CartItem>();
	private double total;
	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void add(CartItem item) {
		double subtotal = item.getSubtotal();//添加项的小计
		String bid = item.getBook().getBid();//项的图书id
		CartItem _item = map.get(bid);//获取原来车的该图书项
		
		if(_item == null) {//如果原来车中没有这个项
			map.put(bid, item);//添加到车中
			total += subtotal;//修改合计，即添加新加项的小计
		} else {//原来车中有这个图书
			_item.setCount(_item.getCount() + item.getCount());//把原来的个数和新加的个数合并
			_item.setSubtotal(_item.getSubtotal() + subtotal);//修改项的小计
			map.put(bid, _item);//再重新放回车里
			total += subtotal;//合计要增加新加项的小计
		}
	}
	
	public void del(String bid) {
		CartItem item = map.remove(bid);
		this.total -= item.getSubtotal();
	}
	
	public void clear() {
		map.clear();
		total = 0;
	}
	
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
}
