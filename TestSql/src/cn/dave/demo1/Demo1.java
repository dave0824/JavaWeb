/**
 * 
 */
package cn.dave.demo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;



/**
 * 测试添加查询数据库元素
 * @author dave
 *
 */
public class Demo1 {

	/**
	 * 添加记录
	 * */
	@Test
	public void test() throws Exception {
		/**
		 * 1.连接数据库的四大参数
		 * */
		String driverClassName ="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/mydb?useUnicode=true&characterEndcoding=utf8&useSSL=false";
		String username = "root";
		String password ="763081703";
		/**
		 * 2.加载驱动
		 * */
		Class.forName(driverClassName);
		/**
		 * 3.得到connection连接
		 * */
		Connection con = DriverManager.getConnection(url,username,password);
		/**
		 * 4.得到statement
		 * */
		Statement stmt = con.createStatement();
		/**
		 * 5.添加一条记录
		 * */
		String sql = "insert into stu values(4,'王五',22,'男')";
		int m = stmt.executeUpdate(sql);
		System.out.println(m);
	}
	/**
	 * 查询记录
	 * */
	@Test
	public void test2() throws Exception {
		/**
		 * 1.连接数据库的四大参数
		 * */
		String driverClassName ="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/mydb?useUnicode=true&characterEndcoding=utf8&useSSL=false";
		String username = "root";
		String password ="763081703";
		/**
		 * 2.加载驱动
		 * */
		Class.forName(driverClassName);
		/**
		 * 3.得到connection连接
		 * */
		Connection con = DriverManager.getConnection(url,username,password);
		/**
		 * 4.得到statement
		 * */
		Statement stmt = con.createStatement();
		/**
		 * 5.查询整张表
		 * */
		String sql = "select * from stu";
		/**
		 * 6.得到ResultSet结果集
		 * */
		ResultSet rs = stmt.executeQuery(sql);
		/**
		 * 7.遍历输出表
		 * */
		System.out.println("sid"+"\t"+"sname"+"\t"+"age"+"\t"+"sex");
		System.out.println("----------------------------------------");
		while(rs.next()){
			int sid = rs.getInt(1);
			String sname = rs.getString("sname");
			int age = rs.getInt("age");
			String sex = (String) rs.getObject(4);
			System.out.println(sid+"\t"+sname+"\t"+age+"\t"+sex);
		}
	}	
}
