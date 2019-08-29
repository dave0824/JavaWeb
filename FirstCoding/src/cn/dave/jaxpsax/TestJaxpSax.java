package cn.dave.jaxpsax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TestJaxpSax {

	public static void main(String[] args) throws Exception{

		/*
		 * 1.��������������
		 * 2.����������
		 * 3.ִ��parse����
		 * 
		 * 4.�Լ�����һ���࣬�̳�DefaultHandler
		 * 5.��д���������������
		 * */
		//������������
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		//����������
		SAXParser saxParser = saxParserFactory.newSAXParser();
		//ִ��parser����
		saxParser.parse("src/person.xml",new MyDefault2());
	}

}

//��ȡ���е�nameֵ
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

//ԭ����ӡ
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
	
