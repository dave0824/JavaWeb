package cn.dave.web.listener;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class AListener
 *
 */
@WebListener
public class AListener implements ServletContextListener {


    public void contextDestroyed(ServletContextEvent arg0)  { 
         
    }


    public void contextInitialized(ServletContextEvent arg0)  { 
        /*
         * 在Tomcat启动时创建map并存放到application中
         * 
         */
    	//创建Map
    	Map<String,Integer> map = new LinkedHashMap<String,Integer>();
    	//得到application
    	ServletContext application = arg0.getServletContext();
    	//吧map加载到application中
    	application.setAttribute("map", map);
    	
    }
	
}
