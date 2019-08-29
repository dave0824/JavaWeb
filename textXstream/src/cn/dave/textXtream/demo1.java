package cn.dave.textXtream;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import cn.dave.domain.City;
import cn.dave.domain.Province;

public class demo1 {

	public List<Province> getProvinceList(){
		Province p1 = new Province();
		p1.setName("江西");
		p1.addCity(new City("南昌","NanChang"));
		p1.addCity(new City("上饶","ShangRao"));
		
		Province p2 = new Province();
		p2.setName("浙江");
		p2.addCity(new City("杭州","HangZhou"));
		p2.addCity(new City("义乌","YiWu"));
		
		List<Province> provinceList = new ArrayList<Province>();
		provinceList.add(p1);
		provinceList.add(p2);
		return provinceList;

	}
	/*
	 * <list>
  <cn.dave.domain.Province>javabean的类型为Province，它元素的名称为类的完整名
    <name>江西</name>javabean的属性名
    <cities>Javabean的属性名
      <cn.dave.domain.City>类名
        <name>南昌</name>
        <description>NanChang</description>
      </cn.dave.domain.City>
      <cn.dave.domain.City>
        <name>上饶</name>
        <description>ShangRao</description>
      </cn.dave.domain.City>
    </cities>
  </cn.dave.domain.Province>
  <cn.dave.domain.Province>
    <name>浙江</name>
    <cities>
      <cn.dave.domain.City>
        <name>杭州</name>
        <description>HangZhou</description>
      </cn.dave.domain.City>
      <cn.dave.domain.City>
        <name>义乌</name>
        <description>YiWu</description>
      </cn.dave.domain.City>
    </cities>
  </cn.dave.domain.Province>
</list>
	 * */
	@Test
	public void test() {
		List<Province> provinceList = getProvinceList();
		/*
		 * 创建XStream对象
		 * 调用toXML把集合转换成xml字符串
		 */
		XStream xstream = new XStream();
		String s = xstream.toXML(provinceList);
		System.out.println(s);
	}

	/*
	 * <chain>
  <province>
    <name>江西</name>
    <cities>
      <city>
        <name>南昌</name>
        <description>NanChang</description>
      </city>
      <city>
        <name>上饶</name>
        <description>ShangRao</description>
      </city>
    </cities>
  </province>
  <province>
    <name>浙江</name>
    <cities>
      <city>
        <name>杭州</name>
        <description>HangZhou</description>
      </city>
      <city>
        <name>义乌</name>
        <description>YiWu</description>
      </city>
    </cities>
  </province>
</chain>
	 */
	@Test
	public void test2(){
		List<Province> provinceList = getProvinceList();
		XStream xstream = new XStream();
		
		/*
		 * 起别名(alias)
		 * 希望<List>变为<chain>
		 * <cn.dave.domain.Province>变为<province>
		 * <cn.dave.domain.City>变为<city>
		 */
		xstream.alias("chain",List.class);//给List类型指定别名为chain
		xstream.alias("province", Province.class);//给Province类型指定别名为province
		xstream.alias("city",City.class);
		
		String s = xstream.toXML(provinceList);
		System.out.println(s);
	}
	
	/*
	 * <chain>
  <province name="江西">
    <cities>
      <city>
        <name>南昌</name>
        <description>NanChang</description>
      </city>
      <city>
        <name>上饶</name>
        <description>ShangRao</description>
      </city>
    </cities>
  </province>
  <province name="浙江">
    <cities>
      <city>
        <name>杭州</name>
        <description>HangZhou</description>
      </city>
      <city>
        <name>义乌</name>
        <description>YiWu</description>
      </city>
    </cities>
  </province>
</chain>
	 */
	@Test
	public void test3(){
		List<Province> provinceList = getProvinceList();
		XStream xstream = new XStream();
		xstream.alias("chain",List.class);//给List类型指定别名为chain
		xstream.alias("province", Province.class);//给Province类型指定别名为province
		xstream.alias("city",City.class);
		xstream.useAttributeFor(Province.class, "name");
		
		/*
		 *  把Province类型的name属性，生成<province>元素的属性
		 */
		xstream.addImplicitCollection(Province.class,"cities");
		String s = xstream.toXML(provinceList);
		System.out.println(s);
	}
	
	/*
<chain>
  <province name="江西">
    <city>
      <name>南昌</name>
      <description>NanChang</description>
    </city>
    <city>
      <name>上饶</name>
      <description>ShangRao</description>
    </city>
  </province>
  <province name="浙江">
    <city>
      <name>杭州</name>
      <description>HangZhou</description>
    </city>
    <city>
      <name>义乌</name>
      <description>YiWu</description>
    </city>
  </province>
</chain>
	 */
	@Test
	public void test4(){
		List<Province> provinceList = getProvinceList();
		XStream xstream = new XStream();
		xstream.alias("chain",List.class);//给List类型指定别名为chain
		xstream.alias("province", Province.class);//给Province类型指定别名为province
		xstream.alias("city",City.class);
		xstream.useAttributeFor(Province.class, "name");//把Province类型的name属性，生成<province>元素的属性
		
		/*
		 * 去除<cities>这样的Collection类型的属性
		 * 去除Provice类的名为cities的List类型的属性！
		 */
		xstream.addImplicitArray(Province.class, "cities");
		String s = xstream.toXML(provinceList);
		System.out.println(s);
	}
}
