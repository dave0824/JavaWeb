package cn.dave.bookstore.admin.service;

import cn.dave.bookstore.admin.dao.AdminDao;
import cn.dave.bookstore.admin.domain.Admin;
import cn.dave.bookstore.user.domain.User;
import cn.dave.bookstore.user.service.UserException;

public class AdminService {

	private AdminDao  adminDao = new AdminDao();

	/**
	 * @throws UserException 
	 * 管理员注册
	 * @author: dave
	 * @date:   2019年5月13日 上午11:04:43
	 * @Description: TODO
	 * @param form void  
	 * @throws
	 */
	public void regist(Admin form) throws UserException {
		/*
		 * 1.检验用户名是否被注册，如果被注册则抛出异常
		 * 2.检验邮箱是否被注册，如果被注册则抛出异常
		 * 3.把数据form保存进数据库，返回null
		 */
		if(adminDao.findByAname(form.getAname()) != null){
	
			throw new UserException("用户名：" + form.getAname() + "已存在！");
		}
	
		adminDao.add(form);
		
	}

	/**
	 * @throws UserException 
	 * 管理员登陆
	 * @author: dave
	 * @date:   2019年5月13日 上午11:42:33
	 * @Description: TODO
	 * @param form
	 * @return Admin  
	 * @throws
	 */
	public Admin login(Admin form) throws UserException {
		/*
		 * 1.通过用户名查找，存在则返回user否则抛异常
		 * 2.比对密码，错误抛异常
		 * 3.比对激活状态，若为激活则抛异常
		 */
		Admin admin = adminDao.findByAname(form.getAname());
		if(admin == null){
			throw new UserException("无此管理员");	
		}
		if(!admin.getPassword().equals(form.getPassword())){
			throw new UserException("密码错误");
		}
		if(!admin.isState()){
			throw new UserException("账号未激活，请先激活！");
		}
		return admin;
	}
}
