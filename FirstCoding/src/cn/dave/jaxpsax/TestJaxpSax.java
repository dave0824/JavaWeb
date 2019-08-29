package cn.dave.jaxpsax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TestJaxpSax {

	public static void main(String[] args) throws Exception{

		/*
		 * 1.创建解析器工厂
		 * 2.创建解析器
		 * 3.执行parse方法
		 * 
		 * 4.自己创建一个类，继承DefaultHandler
		 * 5.重写类里面的三个方法
		 * */
		//创建解析工厂
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		//创建解析器
		SAXParser saxParser = saxParserFactory.newSAXParser();
		//执行parser方法
		saxParser.parse("src/person.xml",new MyDefault2());
	}

}

//获取所有的name值
class MyDefault extends DefaultHandler{
	
	private boolean flag = false;
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(qName.equals("name")){
			flag = true;
		}
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(flag == true){
			System.out.println(new String(ch,start,length));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equals("name")){
			
			flag = false;
		}
	}
}

//原样打印
class MyDefault2  extends DefaultHandler{
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		System.out.print("<"+qName+">");
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		System.out.print(new String(ch,start,length));
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		System.out.print("</"+qName+">");
	}
}
	
