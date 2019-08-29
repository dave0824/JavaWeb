/**
 * 
 */
package test;

import org.junit.Test;

import cn.dave.user.dao.UserDaoImpl;
import cn.dave.user.domain.User;

/**
 * @author dave
 *
 */
public class TestUserDao {

	@Test
	public void test2() {
		UserDaoImpl userDao = new UserDaoImpl();
		//User user = new User();
//		user.setUsername("曹操");
//		user.setPassword("123");
		User user = userDao.findByUsername("aaaaa");
		System.out.println(user);
	}
	@Test
	public void test(){
		UserDaoImpl userDao = new UserDaoImpl();
		User user = new User();
		user.setUsername("李四");
		user.setPassword("lisi");
		userDao.addUser(user);
	}

}
