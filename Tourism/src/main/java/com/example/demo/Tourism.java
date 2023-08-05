package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tourism {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String location;
	private String des;
	private String phone;
	@Column(length=1000000)
	private byte[] image;
	
	
	public Tourism() {
		super();
	}
	public Tourism(int id, String name, String location, String des, String phone,
			byte[] image) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.des = des;
		this.phone = phone;
		this.image = image;
	}
	public Tourism(String name, String location, String des,String phone,
			byte[] image) {
		super();
		this.name = name;
		this.location = location;
		this.des = des;
		this.phone = phone;
		this.image = image;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	
	
	
}