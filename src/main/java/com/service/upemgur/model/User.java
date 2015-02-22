package com.service.upemgur.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@XmlRootElement
@Table(name = "users", indexes = { @Index(name = "PRIMARY", columnList = "username") })
public class User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4925327082843042337L;

	public User() {

	}

	@Id
	@Column(name = "username")
	private String username;

	@JsonIgnore
	@Column(name = "password")
	@NotNull(message = "The property 'password' can not be null")
	private String password;

	@JsonIgnore
	@Column(name = "enabled")
	@NotNull(message = "The property 'enabled' can not be null")
	private int enabled;

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

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

}
