package cn.dave.testJson;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Dome {

	/*
	 * JSON可以当map来用
	 */
	@Test
	public void test() {
		JSONObject map = new JSONObject();
		map.put("name","zhangshan");
		map.put("sex","nv");
		map.put("age", "18");
		
		String s= map.toString();
		System.out.println(s);
	}
	
	/*
	 * 若有person对象，可以把person转为json
	 */
	@Test
	public void test2(){
		
		Person p = new Person();
		p.setName("wanger");
		p.setAge("19");
		p.setSex("nan");
		
		JSONObject map = JSONObject.fromObject(p);
		System.out.println(map.toString());
	
	}
	
	/*
	 * JSONArray可以当数组来用
	 */
	@Test
	public void test3() {
		Person p1 = new Person("lisi","20","male");
		Person p2 = new Person("wanger","21","female");
		
		JSONArray list = new JSONArray();
		list.add(p1);
		list.add(p2);
		System.out.println(list);
		
	}
	
	/*
	 * 若存在list数组可将数组对象转换为JSONArray
	 */
	@Test
	public void test4() {
		List<Person> personList = new ArrayList<Person>();
		personList.add(new Person("zhangsan","12","male"));
		personList.add(new Person("lisi","18","female"));
		
		JSONArray list = JSONArray.fromObject(personList);
		System.out.println(list);

	}
}
