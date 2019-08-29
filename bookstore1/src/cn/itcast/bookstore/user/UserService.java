package cn.itcast.bookstore.user;

public class UserService {
	private UserDao userDao = new UserDao();
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}
	
	public void regist(User user) throws UserException {
		User _user = userDao.findByUsername(user.getUsername());
		// 校验用户名是否已经存在
		if(_user != null) {
			throw new UserException("该用户名已被注册：" + user.getUsername());
		}
		// 校验Email是否已经存在
		_user = userDao.findByEmail(user.getEmail());
		if(_user != null) {
			throw new UserException("该Email已被注册：" + user.getEmail());
		}
		//_user一直null
		// 向数据库添加记录
		userDao.add(user);
	}
	
	public void updateState(String uid, boolean state) {
		userDao.updateState(uid, state);
	}
}
