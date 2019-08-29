/**
 * 
 */
package cn.dave.testSql;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

/**
 * @author dave
 *
 */
public class ConnetSql {

	public static void main(String args[]){
		Connection con;
		String driver ="com.mysql.jdbc.Driver";
		String username = "root";
		String password = "763081703";
		String url="jdbc:mysql://localhost:3306/exam?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
		try{
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url,username,password);
			if(!con.isClosed()){
				System.out.println("Succeeded connection to the database");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
