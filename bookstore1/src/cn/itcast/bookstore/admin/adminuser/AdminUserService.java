package cn.itcast.bookstore.admin.adminuser;

public class AdminUserService {
	private AdminUserDao adminUserDao = new AdminUserDao();
	
	public AdminUser login(String adminname, String password) {
		return adminUserDao.login(adminname, password);
	}
}
