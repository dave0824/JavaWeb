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
	
	//��ȡdocument
	public Document getDocument(String url){
		
		//��������������
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		//���ݽ�������������������
		DocumentBuilder documentBuilder = null;
		try {
			 documentBuilder = documentBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//����xml����document
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
	
	//����һ����д�ķ�װ����
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
	//�õ�ÿһ��NAME��ǩ�е�����
	public void getAllElementValue(){
		
		/*
		 * 1.����getDocument()��������xml����document
		 * 2���õ����е�nameԪ��
			ʹ��document.getElementsByTagName("name");
		 * 3�����ؼ��ϣ��������ϣ��õ�ÿһ��nameԪ��
			- ���� getLength() item()
			- �õ�Ԫ������ֵ ʹ�� getTextContent()
		 * */
		Document document = getDocument("src/person.xml");
		NodeList list = document.getElementsByTagName("name");
		for(int i=0;i<list.getLength();i++ ){
			System.out.println(list.item(i).getTextContent());
			
		}
		
	}
	
	//����һ����ǩ
	public void addElement(){
		/*
			 * 1����������������
			 * 2�����ݽ�������������������
			 * 3������xml������document
			 * 
			 * 4���õ���һ��p1
			 * 	- �õ�����p1��ʹ��item�����±�õ�

			 * 5������sex��ǩ createElement
			 * 6�������ı� createTextNode
			 * 7�����ı���ӵ�sex���� appendChild

			 * 8����sex��ӵ���һ��p1���桡appendChild
			 * 
			 * 9����дxml
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
	//����һ���޸ı�ǩ����ĵ��ݵķ���
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
	
	//��ʾ���еı�ǩ��
	public void showAllElementName(){
		
		Document document = getDocument("src/person.xml");
		showNode(document);
	}
	
	//���õݹ���ʾ���еĽ��
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
