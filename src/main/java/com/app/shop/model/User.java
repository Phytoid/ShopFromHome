package com.app.shop.model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="shop_user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userid;
	private String username;
	private String password;
	@OneToMany(targetEntity = Product.class)
	private List<Product> cart;
	@JoinColumn(name="product_key", referencedColumnName="userid")
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}