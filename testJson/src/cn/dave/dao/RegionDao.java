package cn.dave.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.dave.domain.City;
import cn.dave.domain.Province;
import cn.itcast.jdbc.TxQueryRunner;

public class RegionDao {
	QueryRunner qr = new TxQueryRunner();

	/**
	 * 
	* @Title: findAll 
	* @Description: 查询所有省 
	* @param @return
	* @return List<Province>
	* @throws
	 */
	public List<Province> findAll() {

		try {
			String sql = "SELECT * FROM t_province";
			return qr.query(sql, new BeanListHandler<Province>(Province.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	* @Title: findById 
	* @Description: 通过省对象的pid查找城市 
	* @param @param id
	* @param @return
	* @return List<City>
	* @throws
	 */
	public List<City> findById(int id){
		try{
			String sql = "SELECT * FROM t_city WHERE pid=?";
			return qr.query(sql, new BeanListHandler<City>(City.class),id);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
