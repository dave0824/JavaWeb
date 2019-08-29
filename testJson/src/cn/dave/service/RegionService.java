package cn.dave.service;

import java.util.List;

import cn.dave.dao.RegionDao;
import cn.dave.domain.City;
import cn.dave.domain.Province;

public class RegionService {
	RegionDao dao = new RegionDao();
	
	public List<Province> findAll(){
		
		return dao.findAll();
		
	}
	
	public List<City> findById(int id){
		
		return dao.findById(id);
	}
}
