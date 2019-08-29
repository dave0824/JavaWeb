package cn.dave.bookstore.user.service;

import cn.dave.bookstore.user.dao.UserDao;
import cn.dave.bookstore.user.domain.User;

public class UserService {
	private UserDao userDao = new UserDao();
	
	/**
	 * 
	* @Title: regist 
	* @Description: 注册 
	* @param @param form
	* @param @throws UserException
	* @return void
	* @throws
	 */
	public void regist(User form) throws UserException{
		/*
		 * 1.检验用户名是否被注册，如果被注册则抛出异常
		 * 2.检验邮箱是否被注册，如果被注册则抛出异常
		 * 3.把数据form保存进数据库，返回null
		 */
		if(userDao.findByUsername(form.getUsername()) != null){
	
			throw new UserException("用户名：" + form.getUsername() + "已存在！");
		}
		if(userDao.findByEmail(form.getEmail()) != null){
			throw new UserException("邮箱：" + form.getEmail() + "已被注册");
		}
		
		userDao.add(form);
	
	}
	
	/**
	 * 
	 * @author: dave
	 * @date:   2019年5月6日 上午10:31:24
	 * @Description: 通过邮箱和激活码一一校验激活，激活安全级别更高 
	 * @param email
	 * @param code
	 * @return
	 * @throws UserException int  
	 * @throws
	 */
	public int findByEmail(String email, String code) throws  UserException {
		
		User user = userDao.findByEmail(email,code);
		if(user == null){
			throw new UserException("激活码或邮箱错误，请重试！");
		}
		if(user.isState()){
			throw new UserException("您已经激活过了，请不要重复激活!");
		}else{
			return userDao.updateState(user.getUid(), true);
		}
	}

	/**
	 * 
	* @Title: findByEmail 
	* @Description: 通过邮箱查找用户
	* @param @param email
	* @param @return
	* @param @throws UserException
	* @return User
	* @throws
	 */
	public User findByEmail(String email) throws UserException {
		
		if(userDao.findByEmail(email) != null){
			
			return userDao.findByEmail(email);
			
		}else{
			throw new UserException("邮箱：" + email+ "还没注册，请先注册！");
		}
		
	}
	
	/**
	 * 
	 * @author: dave
	 * @date:   2019年5月6日 下午3:37:37
	 * @Description: 用户登录校验
	 * @param form
	 * @return
	 * @throws UserException User  
	 * @throws
	 */
	public User login(User form) throws UserException{
		/*
		 * 1.通过用户名查找，存在则返回user否则抛异常
		 * 2.比对密码，错误抛异常
		 * 3.比对激活状态，若为激活则抛异常
		 */
		User user = userDao.findByUsername(form.getUsername());
		if(user == null){
			throw new UserException("用户名不存在");	
		}
		if(!user.getPassword().equals(form.getPassword())){
			throw new UserException("密码错误");
		}
		if(!user.isState()){
			throw new UserException("账号未激活，请先激活！");
		}
		return user;
	}

}
