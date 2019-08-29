package cn.dave.bookstore.admin.domain;

public class Admin {
	private String aid;//管理员ID
	private String aname;//管理员名字
	private String password;//管理员密码
	private boolean state;//管理员是否被超管激活
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Admin [aid=" + aid + ", aname=" + aname + ", password=" + password + ", state=" + state + "]";
	}
	
}
