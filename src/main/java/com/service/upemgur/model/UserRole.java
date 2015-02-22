package com.service.upemgur.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
@Table(name = "user_roles", indexes = { @Index(name = "PRIMARY", columnList = "user_role_id") , @Index(name = "fk_username_idx", columnList = "username") })
public class UserRole implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5273319360910513568L;


	public static final String ROLE_USER = "ROLE_USER";
	
	
	@Id
	@Column(name = "user_role_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int user_role_id;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "username")
	private User user;
	
	
	@Column(name = "role")
	@NotNull(message = "The property 'role' can not be null")
	private String role;
	
	
	public UserRole(){
		
	}


	public int getUser_role_id() {
		return user_role_id;
	}


	public void setUser_role_id(int user_role_id) {
		this.user_role_id = user_role_id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}

	
	
}

