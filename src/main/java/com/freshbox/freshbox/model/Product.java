package com.freshbox.freshbox.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "table_product")

public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Table_Product_Id")
	private int Product_Id;
	@Column(name="Table_Product_Name")
	private String Product_Name;
	@Column(name="Table_Product_Price")
	private int Product_Price;
	@Column(name="Table_Product_Category")
	private String Product_Category;
	@Column(name="Table_Product_Type")
	private String Product_Type;
	@Column(name="Table_Product_Availability")
	private String Product_Availability;
	@Column(name="Table_Product_Cuisine")
	private String Product_Cuisine;
	@Column(name="Table_Product_Description")
	private String Product_Description;
	@Column(name="Table_Product_Offers")
	private int Product_Offers;
	
	public Product(int product_Id, String product_Name, int product_Price, String product_Category, String product_Type,
			String product_Availability, String product_Cuisine, String product_Description, int product_Offers) {
		Product_Id = product_Id;
		Product_Name = product_Name;
		Product_Price = product_Price;
		Product_Category = product_Category;
		Product_Type = product_Type;
		Product_Availability = product_Availability;
		Product_Cuisine = product_Cuisine;
		Product_Description = product_Description;
		Product_Offers = product_Offers;
	}
	
	public Product(String product_Name, int product_Price, String product_Category, String product_Type,
			String product_Availability, String product_Cuisine, String product_Description, int product_Offers) {
		Product_Name = product_Name;
		Product_Price = product_Price;
		Product_Category = product_Category;
		Product_Type = product_Type;
		Product_Availability = product_Availability;
		Product_Cuisine = product_Cuisine;
		Product_Description = product_Description;
		Product_Offers = product_Offers;
	}
	
	
	
	public int getProduct_Id() {
		return Product_Id;
	}



	public void setProduct_Id(int product_Id) {
		Product_Id = product_Id;
	}



	public String getProduct_Name() {
		return Product_Name;
	}



	public void setProduct_Name(String product_Name) {
		Product_Name = product_Name;
	}



	public int getProduct_Price() {
		return Product_Price;
	}



	public void setProduct_Price(int product_Price) {
		Product_Price = product_Price;
	}



	public String getProduct_Category() {
		return Product_Category;
	}



	public void setProduct_Category(String product_Category) {
		Product_Category = product_Category;
	}



	public String getProduct_Type() {
		return Product_Type;
	}



	public void setProduct_Type(String product_Type) {
		Product_Type = product_Type;
	}



	public String getProduct_Availability() {
		return Product_Availability;
	}



	public void setProduct_Availability(String product_Availability) {
		Product_Availability = product_Availability;
	}



	public String getProduct_Cuisine() {
		return Product_Cuisine;
	}



	public void setProduct_Cuisine(String product_Cuisine) {
		Product_Cuisine = product_Cuisine;
	}



	public String getProduct_Description() {
		return Product_Description;
	}



	public void setProduct_Description(String product_Description) {
		Product_Description = product_Description;
	}



	public int getProduct_Offers() {
		return Product_Offers;
	}



	public void setProduct_Offers(int product_Offers) {
		Product_Offers = product_Offers;
	}



	public Product() {
		
	}

	@Override
	public String toString() {
		return "Product [Product_Id=" + Product_Id + ", Product_Name=" + Product_Name + ", Product_Price="
				+ Product_Price + ", Product_Category=" + Product_Category + ", Product_Type=" + Product_Type
				+ ", Product_Availability=" + Product_Availability + ", Product_Cuisine=" + Product_Cuisine
				+ ", Product_Description=" + Product_Description + ", Product_Offers=" + Product_Offers + "]";
	}
	
	
	
	
	
	
}
