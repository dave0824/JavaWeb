package cn.dave.domain;

public class City {
	private int cid;
	private String cname;
	private Province province;
	@Override
	public String toString() {
		return "City [cid=" + cid + ", cname=" + cname + ", province=" + province + "]";
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	
}
