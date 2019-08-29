package cn.dave.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 
* Title: AListener
* Description:ServletContext的生命周期监听 ，可以在这里放一下Tomcat启动时就要执行的代码
* Company: 
* @author dave
* @date 2019年4月22日
 */
public class AListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		System.out.println("我要挂了");

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("我出生了哈哈哈");
	}

}
