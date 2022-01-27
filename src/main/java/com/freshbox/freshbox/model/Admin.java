package com.freshbox.freshbox.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "table_admin")
public class Admin {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Table_Admin_Id")
	private int Admin_Id;
	@Column(name="Table_Admin_Name")
	private String Admin_Name;
	@Column(name="Table_Admin_Password")
	private String Admin_Password;
	
	public int getAdmin_Id() {
		return Admin_Id;
	}
	public void setAdmin_Id(int admin_Id) {
		Admin_Id = admin_Id;
	}
	public String getAdmin_Name() {
		return Admin_Name;
	}
	public void setAdmin_Name(String admin_Name) {
		Admin_Name = admin_Name;
	}
	public String getAdmin_Password() {
		return Admin_Password;
	}
	public void setAdmin_Password(String admin_Password) {
		Admin_Password = admin_Password;
	}
	
	public Admin(int admin_Id, String admin_Name, String admin_Password) {
		Admin_Id = admin_Id;
		Admin_Name = admin_Name;
		Admin_Password = admin_Password;
	}

	public Admin() {
	
	}
	@Override
	public String toString() {
		return "Admin [Admin_Id=" + Admin_Id + ", Admin_Name=" + Admin_Name + ", Admin_Password=" + Admin_Password
				+ "]";
	}
	
	
	
	
}
