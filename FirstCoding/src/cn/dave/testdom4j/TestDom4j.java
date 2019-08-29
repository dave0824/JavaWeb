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
	//ɾ���ڶ���p1
	public static void removeNode(){
		
		/*
		 * 1.�õ�document
		 * 2.�õ����ڵ�
		 * 3.�õ��ڶ���p1
		 * 4.ͨ��p1�ĸ��ڵ�Ҳ���Ǹ��ڵ�ɾ���ڶ���p1
		 * 5.��дdocument
		 * */
		// �õ�document
		Document document = Dom4jUtils.getDocument();
		//�õ����ڵ�
		Element root = document.getRootElement();
		//�õ��ڶ���p1
/*		List<Element> p1 = root.elements("p1");
		Element p12 =p1.get(1);*/
		Element p12 = (Element) root.elements("p1").get(1);
		//ͨ��root���ɾ��p12
		root.remove(p12);
		//��дdocument
		Dom4jUtils.xmlWriters(document);
	}
	
	//�޸ĵ�һ��name��ֵ
	public static void motifyValue(){
		/*
		 *1.�õ�document
		 *2.�õ����ڵ�
		 *3.�õ�p1���
		 *4.�õ�name���
		 *5.�޸�name�������
		 *6.��дdocument
		 * */
		//�õ�document
		Document document = Dom4jUtils.getDocument();
		//�õ����ڵ�
		Element root = document.getRootElement();
		//�õ�p1���
		Element p1 = root.element("p1");
		//�õ�name���
		Element name = p1.element("name");
		//�޸�name���ı�ֵ
		name.setText("xiaohong");
		//��дdocument
		Dom4jUtils.xmlWriters(document);
		
	}
	//��p1ĩβ���һ��Ԫ��<sex>man<sex>
	public static void addElement(){
		
		/*
		 * 1.�õ�document
		 * 2.�õ����ڵ�
		 * 3.�õ���һ��p1�ڵ�
		 * 4.��p1�����Ԫ��
		 * 5.����ӵ�Ԫ��������ı�
		 * 6.д��document
		 * */
		
		//�õ�document
		Document document = Dom4jUtils.getDocument();
		//�õ����ڵ�
		Element root = document.getRootElement();
		//�õ���һ��p1
		Element p1 = root.element("p1");
		//���һ����ǩ
		Element sex = p1.addElement("sex");
		//��sex��ǩ������ı�
		sex.setText("man");
		//д��document
		Dom4jUtils.xmlWriters(document);
		
	}
	//��ѯ���е�nameֵ
	public static void selectName(){
		
		/*
		 *1.�õ�document
		 *2.�õ����ڵ�
		 *3.�õ�p1
		 *4.�õ�p1�µ�name
		 *5.�õ�name��ֵ 
		 * */
		//�õ�document
		Document document = Dom4jUtils.getDocument();
		//�õ����ڵ�
		Element root = document.getRootElement();
		//�õ�p1
		@SuppressWarnings("unchecked")
		List<Element> list = root.elements("p1");
		for (Element element : list) {
			
			Element name = element.element("name");
			String str = name.getText();
			System.out.println(str);
		}
		
	}
}
