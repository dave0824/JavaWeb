package cn.dave.user.dao;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.taglibs.standard.tag.common.core.ForEachSupport;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import cn.dave.user.domain.User;

/**
 * 数据操作类
 * */
public class UserDaoImpl implements UserDao {
	private String path = "E:/users.xml";

	public User findByUsername(String username){
		/**
		 * 1.创建解析器
		 * 2.得到document
		 * 3.根据xpath进行比较得到名字相符的user
		 * 4.检查user是否为空，是则直接返回
		 *   不是则返回user
		 * */
		SAXReader reader = new SAXReader();
		try {
			Document doc=reader.read(path);
			Element root = doc.getRootElement();
			
			@SuppressWarnings("unchecked")
			
			List<Element> usernames = root.elements("user");
			Element ele=null;
			if(usernames.size() > 0){
				for (Element element : usernames) {
					if(element.element("username").getData().equals(username)){
						ele = element;
					}
				}
			}
			if(ele == null)
				return null;
			User user = new User();
			String attrUsername = (String) ele.element("username").getData();
			String attrPassword = (String) ele.element("password").getData();
			user.setPassword(attrPassword);
			user.setUsername(attrUsername);
			return user;
		} catch (DocumentException e) {
			
			throw new RuntimeException(e);
		}
		
	}
	
	public void addUser(User user){
		
		/**
		 * 1.得到document
		 * 2.得到root
		 * 3.使用参数user，转发成Element对象
		 * 4.保存element添加到root元素中
		 * 5.保存document
		 * */
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(path);
			Element root = doc.getRootElement();
			Element userEle = root.addElement("user");
			Element userN = userEle.addElement("username");
			Element userP = userEle.addElement("password");
			userN.setText(user.getUsername());
			userP.setText(user.getPassword());
			
			//回写
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(path),format);
			xmlWriter.write(doc);
			xmlWriter.close();
		    }catch(Exception e){
		    	throw new RuntimeException();
		    }
	 }
}

