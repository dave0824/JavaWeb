package cn.dave.domain;

import java.util.ArrayList;
import java.util.List;

public class Province {
	private String name;
	List<City> cities = new ArrayList<City>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<City> getCities() {
		return cities;
	}
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	@Override
	public String toString() {
		return "Province [name=" + name + ", cities=" + cities + "]";
	}
	/**
	 * @param name
	 * @param cities
	 */
	public Province(String name, List<City> cities) {
		super();
		this.name = name;
		this.cities = cities;
	}
	/**
	 * 
	 */
	public Province() {
		super();
	}
	public void addCity(City city) {
		
		cities.add(city); 
		
	}
	
}
