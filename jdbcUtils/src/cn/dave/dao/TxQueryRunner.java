package cn.dave.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import cn.dave.jdbcUtils3.JdbcUtils;

/**
 * 这个类中的方法，自己来处理连接的问题
 * 无需外界传递！　
 * 怎么处理的呢？
 *   通过JdbcUtils.getConnection()得到连接！有可能是事务连接，也可能是普通的连接！
 *   JdbcUtils.releaseConnection()完成对连接的释放！如果是普通连接，关闭之！
 * @author dave
 *
 */
public class TxQueryRunner extends QueryRunner{

	@Override
	public int[] batch(Connection conn, String sql, Object[][] params) throws SQLException {
		/*
		 * 1. 得到连接
		 * 2. 执行父类方法，传递连接对象
		 * 3. 释放连接
		 * 4. 返回值
		 */
		Connection con = JdbcUtils.getConnection();
		int[] result =  super.batch(conn, sql, params);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		int[] result =  super.batch(con, sql, params);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public <T> T query(Connection conn, String sql, Object param, ResultSetHandler<T> rsh) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		T result = super.query(conn, sql, param, rsh);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public <T> T query(Connection conn, String sql, Object[] params, ResultSetHandler<T> rsh) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		T result = super.query(conn, sql, params, rsh);
		JdbcUtils.releaseConnection(con);
		return result;

	}

	@Override
	public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		T result = super.query(conn, sql, params, rsh);
		JdbcUtils.releaseConnection(con);
		return result;

	}

	@Override
	public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh) throws SQLException {
		// TODO Auto-generated method stub
		return super.query(conn, sql, rsh);
	}

	@Override
	public <T> T query(String sql, Object param, ResultSetHandler<T> rsh) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = JdbcUtils.getConnection();
		T result  = super.query(con,sql, param, rsh);
		JdbcUtils.releaseConnection(con);
		return result;

	}

	@Override
	public <T> T query(String sql, Object[] params, ResultSetHandler<T> rsh) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		T result  = super.query(con,sql, params, rsh);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		T result  = super.query(con,sql, params, rsh);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		T result  = super.query(con,sql, rsh);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public int update(Connection conn, String sql) throws SQLException {
		// TODO Auto-generated method stub
		return super.update(conn, sql);
	}

	@Override
	public int update(Connection conn, String sql, Object param) throws SQLException {
		// TODO Auto-generated method stub
		return super.update(conn, sql, param);
	}

	@Override
	public int update(Connection conn, String sql, Object... params) throws SQLException {
		// TODO Auto-generated method stub
		return super.update(conn, sql, params);
	}

	@Override
	public int update(String sql) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		int result = super.update(con, sql);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public int update(String sql, Object param) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		int result = super.update(con, sql, param);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public int update(String sql, Object... params) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		int result = super.update(con,sql,params);
		JdbcUtils.releaseConnection(con);
		return result;
	}
	
}
