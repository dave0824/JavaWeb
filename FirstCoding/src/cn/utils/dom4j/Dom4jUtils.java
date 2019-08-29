package cn.utils.dom4j;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jUtils {
	
	public static final String PATH = "src/Animals.xml";
	
	//����document
	public static Document getDocument(){
		//��ȡ������
		SAXReader reader = new SAXReader();
		//�õ�document
		try {
			Document document = reader.read(PATH);
			return document;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//д��document
	public static void xmlWriters(Document document){
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		try {
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(PATH),format);
			try {
				xmlWriter.write(document);
				xmlWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
