package cn.dave.testdom4j;


import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.utils.dom4j.Dom4jUtils;

public class TestDom4j {
	
	public static void main(String[] args){
		//selectName();
		//addElement();
		//motifyValue();
		removeNode();
	}
	//删除第二个p1
	public static void removeNode(){
		
		/*
		 * 1.得到document
		 * 2.得到根节点
		 * 3.得到第二个p1
		 * 4.通过p1的父节点也就是根节点删除第二个p1
		 * 5.回写document
		 * */
		// 得到document
		Document document = Dom4jUtils.getDocument();
		//得到根节点
		Element root = document.getRootElement();
		//得到第二个p1
/*		List<Element> p1 = root.elements("p1");
		Element p12 =p1.get(1);*/
		Element p12 = (Element) root.elements("p1").get(1);
		//通过root结点删除p12
		root.remove(p12);
		//回写document
		Dom4jUtils.xmlWriters(document);
	}
	
	//修改第一个name的值
	public static void motifyValue(){
		/*
		 *1.得到document
		 *2.得到根节点
		 *3.得到p1结点
		 *4.得到name结点
		 *5.修改name结点内容
		 *6.回写document
		 * */
		//得到document
		Document document = Dom4jUtils.getDocument();
		//得到根节点
		Element root = document.getRootElement();
		//得到p1结点
		Element p1 = root.element("p1");
		//得到name结点
		Element name = p1.element("name");
		//修改name的文本值
		name.setText("xiaohong");
		//回写document
		Dom4jUtils.xmlWriters(document);
		
	}
	//在p1末尾添加一个元素<sex>man<sex>
	public static void addElement(){
		
		/*
		 * 1.得到document
		 * 2.得到根节点
		 * 3.得到第一个p1节点
		 * 4.在p1下添加元素
		 * 5.在添加的元素下添加文本
		 * 6.写回document
		 * */
		
		//得到document
		Document document = Dom4jUtils.getDocument();
		//得到根节点
		Element root = document.getRootElement();
		//得到第一个p1
		Element p1 = root.element("p1");
		//添加一个标签
		Element sex = p1.addElement("sex");
		//再sex标签下添加文本
		sex.setText("man");
		//写回document
		Dom4jUtils.xmlWriters(document);
		
	}
	//查询所有的name值
	public static void selectName(){
		
		/*
		 *1.得到document
		 *2.得到根节点
		 *3.得到p1
		 *4.得到p1下的name
		 *5.得到name的值 
		 * */
		//得到document
		Document document = Dom4jUtils.getDocument();
		//得到根节点
		Element root = document.getRootElement();
		//得到p1
		@SuppressWarnings("unchecked")
		List<Element> list = root.elements("p1");
		for (Element element : list) {
			
			Element name = element.element("name");
			String str = name.getText();
			System.out.println(str);
		}
		
	}
}
