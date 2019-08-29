/**
 * 
 */
package test;

import org.junit.Test;

import cn.dave.user.domain.User;
import cn.dave.user.service.UserException;
import cn.dave.user.service.UserService;

/**
 * @author dave
 *
 */
public class TestUserService {

	@Test
	public void test() {
		UserService userService = new UserService();
		User user = new User();
		user.setUsername("xxx");
		user.setPassword("123");
		try {
			userService.regist(user);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
