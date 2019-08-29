package cn.dave.listener2;

import java.io.Serializable;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * Application Lifecycle Listener implementation class User
 *
 */
@WebListener
public class User implements HttpSessionActivationListener, Serializable {


    public void sessionDidActivate(HttpSessionEvent arg0)  { 
         System.out.println("我和session一起被激活了");
    }

	/**
     * @see HttpSessionActivationListener#sessionWillPassivate(HttpSessionEvent)
     */
    public void sessionWillPassivate(HttpSessionEvent arg0)  { 
         System.out.println("我和session一起被钝化了存放到Tomcat.work.Catalina.localhost.listener.mysession");
    } 
	
}
