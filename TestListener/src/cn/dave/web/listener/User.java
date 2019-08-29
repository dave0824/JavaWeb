package cn.dave.web.listener;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class User implements HttpSessionBindingListener {
	
	private String userame;
	private String password;
	
	public String getUserame() {
		return userame;
	}

	public void setUserame(String userame) {
		this.userame = userame;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [userame=" + userame + ", password=" + password + ", getUserame()=" + getUserame()
				+ ", getPassword()=" + getPassword() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	/**
	 * @param userame
	 * @param password
	 */
	public User(String userame, String password) {
		super();
		this.userame = userame;
		this.password = password;
	}
	public User(){
		
	}
	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		
		System.out.println("娃哈哈，session看上了我!");
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		System.out.println("呜呜呜~ session要和我divoce");

	}

}
