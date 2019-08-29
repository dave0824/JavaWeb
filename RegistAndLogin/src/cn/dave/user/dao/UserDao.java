/**
 * 
 */
package cn.dave.user.dao;

import cn.dave.user.domain.User;

/**
 * @author dave
 *
 */
public interface UserDao {
	public User findByUsername(String username);
	public void addUser(User user);

}
