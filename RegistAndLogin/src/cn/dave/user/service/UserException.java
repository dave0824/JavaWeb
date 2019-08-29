/**
 * 
 */
package cn.dave.user.service;

/**
 * 自定义异常
 * 只需要给出父类的异常构造器即可，方便用来创建对象
 * @author dave
 *
 */
public class UserException extends Exception {

	/**
	 * 
	 */
	public UserException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public UserException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public UserException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public UserException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public UserException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

		
}
