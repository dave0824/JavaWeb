package cn.dave.domain;

public class City {
	private String name;
	private String description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @param name
	 * @param description
	 */
	public City(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	/**
	 * 
	 */
	public City() {
		super();
	}
	@Override
	public String toString() {
		return "City [name=" + name + ", description=" + description + "]";
	}
	
}
