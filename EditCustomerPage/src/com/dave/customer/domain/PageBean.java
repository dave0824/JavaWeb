package com.dave.customer.domain;

import java.util.List;

public class PageBean<T> {

	private int pc; //当前页码pageCode
	//private int tp; //总页数totalPage
	private int tr; //总记录数totalRecode
	private int ps; //每页记录数pageSize
	private List<T> beanList; //当前页的记录数
	
	private String url; //记录查询条件query的url用于回调

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public int getTp() {
		return this.tr%this.ps==0?this.tr/this.ps : this.tr/this.ps+1;
	}


	public int getTr() {
		return tr;
	}

	public void setTr(int tr) {
		this.tr = tr;
	}

	public int getPs() {
		return ps;
	}

	public void setPs(int ps) {
		this.ps = ps;
	}

	public List<T> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
