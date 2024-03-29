/**
 * 
 */
package cn.dave.jdbcUtils1;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * version="1.0"
 * @author dave
 *
 */
public class JdbcUtils {
	private static Properties props = null;
	static{
		// 给props进行初始化，即加载dbconfig.properties文件到props对象中
		try{
			InputStream in = JdbcUtils.class.getClassLoader()
					.getResourceAsStream("dbconfig.properties");
			props = new Properties();
			props.load(in);
		}catch(IOException e){
			throw new RuntimeException(e);
		}
		//加载驱动类
		try{
			Class.forName(props.getProperty("driverClassName"));
		}catch(ClassNotFoundException e){
			throw new RuntimeException(e);
		}
	}
	//获取连接
	public static Connection getConnection() throws SQLException{
		//得到connection
		return DriverManager.getConnection(props.getProperty("url"),
				props.getProperty("username"), props.getProperty("password"));
	}
}
