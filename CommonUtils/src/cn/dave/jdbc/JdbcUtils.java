/**
 * 
 */
package cn.dave.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * version 2.0
 * @author dave
 *
 */
public class JdbcUtils {
	//配置文件的默认配置，必须给出c3p0-config.xml
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	/**
	 * 使用连接池对象返回一个连接对象
	 * */
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	
	/**
	 * 返回连接池对象
	 * */
	public static DataSource getDataSource(){
		return dataSource;
	}
}
