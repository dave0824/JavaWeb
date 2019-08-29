package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 静态创建sessionFactory
 * @className：HibernateUtils.java
 * @Title:     HibernateUtils
 * @Description: TODO 
 * @Company:   dave集团
 * @author:    dave
 * @version:   v1.0
 * @date:      2019年5月14日下午8:38:16
 */
public class HibernateUtils {

	public static final Configuration cfg;
	public static final SessionFactory sf;
	
	static{
		cfg = new Configuration().configure();
		sf = cfg.buildSessionFactory();
	}
	
	public static Session openSession(){
		return sf.openSession();
	}
}
