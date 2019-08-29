package cn.dave.user.service;

import cn.dave.user.dao.DaoFactory;
import cn.dave.user.dao.UserDao;
import cn.dave.user.domain.User;

/*
 * 业务的逻辑层
 * */
public class UserService {
	private UserDao userDao = DaoFactory.getUserDao();
	
	public void regist(User user) throws UserException{
		
		/**
		 * 用用户名进行查询，如果返回的不为空，则抛出异常，否则添加数据到xml文件
		 * 
		 * */
		User _user = userDao.findByUsername(user.getUsername());
		if(_user != null)
			throw new UserException("用户名"+user.getUsername()+"已经被注册");
		else
			userDao.addUser(user);
	}
	/**
	 * 登录功能
	 * @throws UserException 
	 * */
	public User login(User form) throws UserException{
		//使用form中的User进行查询,得到User
		User user = userDao.findByUsername(form.getUsername());
		//如果返回为null，则用户不存在
		if(user == null)throw new UserException("用户名"+form.getUsername()+"不存在");
		//如果不为null，则比较密码信息
		if(!form.getPassword().equals(user.getPassword()))
			throw new UserException("密码错误");
		//返回数据库中查询出来的user，而不是form，因为form中只有用户名和密码，而user是所有用户信息
		return user;
		
	}
}
