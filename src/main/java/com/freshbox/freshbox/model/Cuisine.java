package com.freshbox.freshbox.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "table_cuisines")
public class Cuisine {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Table_Cuisine_Id")
	private int cuisine_id;
	@Column(name="Table_Cuisine_Name")
	private String cuisine_name;
	
	public int getCuisine_id() {
		return cuisine_id;
	}
	public void setCuisine_id(int cuisine_id) {
		this.cuisine_id = cuisine_id;
	}
	public String getCuisine_name() {
		return cuisine_name;
	}
	public void setCuisine_name(String cuisine_name) {
		this.cuisine_name = cuisine_name;
	}
	
	public Cuisine(int cuisine_id, String cuisine_name) {
		this.cuisine_id = cuisine_id;
		this.cuisine_name = cuisine_name;
	}
	
	public Cuisine(String cuisine_name) {
		
		this.cuisine_name = cuisine_name;
	}
	
	public Cuisine() {
	}
	@Override
	public String toString() {
		return "Cuisine [cuisine_id=" + cuisine_id + ", cuisine_name=" + cuisine_name + "]";
	}
	
}
