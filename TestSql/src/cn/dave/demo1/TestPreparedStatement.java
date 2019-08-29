/**
 * 
 */
package cn.dave.demo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import cn.dave.jdbcUtils.JdbcUtils;


/**
 * 防止SQL注入
 * @author dave
 *
 */
public class TestPreparedStatement {

	/**
	 * SQL注入演示
	 * */
	@Test
	public void test3() throws Exception{
		
		Connection con = JdbcUtils.getConnection();
		Statement stmt = con.createStatement();
		String ssname = "a' or 'a'='a";
		String ssid = "a' or 'a'='a";
		String sql = "select * from stu where sname = '"+ssname+"' and sid = '"+ssid+"'";
		ResultSet rs = stmt.executeQuery(sql);
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
		 * 4.创建sql模板
		 * */
		String sql = "insert stu values(?,?,?,?)";
		/**
		 * 5.得到PreparedStatement
		 * */
		PreparedStatement pstmt = con.prepareStatement(sql);
		/**
		 * 5.添加一条记录
		 * */
		pstmt.setInt(1, 6);
		pstmt.setString(2, "小红");
		pstmt.setInt(3, 22);
		pstmt.setString(4, "女");
		int m = pstmt.executeUpdate();
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
		 * 4.得到sql模板
		 * */
		String sql = "select * from stu where sname=?";
		/**
		 * 5.得到PreparedStatement
		 * */
		PreparedStatement pstmt = con.prepareStatement(sql);
		/**
		 * 6.得到ResultSet结果集
		 * */
		pstmt.setString(1,"小红");
		ResultSet rs = pstmt.executeQuery();
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
