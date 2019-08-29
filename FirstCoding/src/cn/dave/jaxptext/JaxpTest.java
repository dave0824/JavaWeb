package cn.dave.jaxptext;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * 
 * @author Administrator
 *
 */

class TestJaxpOfDom{
	
	//获取document
	public Document getDocument(String url){
		
		//创建解析器工厂
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		//根据解析器工厂创建解析器
		DocumentBuilder documentBuilder = null;
		try {
			 documentBuilder = documentBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//解析xml返回document
		Document document = null;
		try {
			 document = documentBuilder.parse(url);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
		
	}
	
	//这是一个回写的封装方法
	private void writeBack(Document document,String url) throws TransformerFactoryConfigurationError, TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			 transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transformer.transform(new DOMSource(document), new StreamResult(url));
	}
	//得到每一个NAME标签中的内容
	public void getAllElementValue(){
		
		/*
		 * 1.调用getDocument()方法解析xml返回document
		 * 2、得到所有的name元素
			使用document.getElementsByTagName("name");
		 * 3、返回集合，遍历集合，得到每一个name元素
			- 遍历 getLength() item()
			- 得到元素里面值 使用 getTextContent()
		 * */
		Document document = getDocument("src/person.xml");
		NodeList list = document.getElementsByTagName("name");
		for(int i=0;i<list.getLength();i++ ){
			System.out.println(list.item(i).getTextContent());
			
		}
		
	}
	
	//增加一个标签
	public void addElement(){
		/*
			 * 1、创建解析器工厂
			 * 2、根据解析器工厂创建解析器
			 * 3、解析xml，返回document
			 * 
			 * 4、得到第一个p1
			 * 	- 得到所有p1，使用item方法下标得到

			 * 5、创建sex标签 createElement
			 * 6、创建文本 createTextNode
			 * 7、把文本添加到sex下面 appendChild

			 * 8、把sex添加到第一个p1下面　appendChild
			 * 
			 * 9、回写xml
			 * */
		Document document = getDocument("src/person.xml");
		NodeList list = document.getElementsByTagName("p1");
		Node list1 = list.item(0);
		Element  element = document.createElement("birthday");
		Text text = document.createTextNode("20");
		element.appendChild(text);
		list1.appendChild(element);
		try {
			writeBack(document,"src/person.xml");
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//这是一个修改标签里面的的容的方法
	public void modifyElementContent(String ElementName){
		Document document = getDocument("src/person.xml");
		NodeList List = document.getElementsByTagName(ElementName);
		Node list1 = List.item(0);
		list1.setTextContent("22");
		try {
			writeBack(document,"src/person.xml");
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//显示所有的标签名
	public void showAllElementName(){
		
		Document document = getDocument("src/person.xml");
		showNode(document);
	}
	
	//运用递归显示所有的结点
	public void showNode(Node document){
		
		if(document.getNodeType()==Node.ELEMENT_NODE){
			System.out.println(document.getNodeName());
		}
		NodeList list = document.getChildNodes();
		for(int i=0;i<list.getLength();i++){
			
			showNode(list.item(i));
		}
		
	}

}

public class JaxpTest {
	
	public static void main(String[] args){
		TestJaxpOfDom testJaxpOfDom = new TestJaxpOfDom();
		//testJaxpOfDom.getAllElementValue();
		//testJaxpOfDom.addElement();
		testJaxpOfDom.modifyElementContent("birthday");
		testJaxpOfDom.showAllElementName();
	}
	
}
