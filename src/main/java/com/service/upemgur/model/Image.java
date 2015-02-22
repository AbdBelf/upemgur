package com.service.upemgur.model;

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
@Table(name = "images", indexes = { @Index(name = "usernameFK_idx", columnList = "username"), @Index(name = "PRIMARY", columnList = "id") })
public class Image {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5567013519137719649L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "username")
	private User user;
	
	@Column(name = "URL")
	@NotNull(message = "The property 'URL' can not be null")
	private String URL;
	
	@Column(name = "exif")
	@NotNull(message = "The property 'exif' can not be null")
	private String exif;
	
	@Column(name = "public")
	@NotNull(message = "The property 'publicImage' can not be null")
	private int publicImage;
	
	public Image(){
		
	}
	
	

	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public int getPublicImage() {
		return publicImage;
	}



	public void setPublicImage(int publicImage) {
		this.publicImage = publicImage;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getExif() {
		return exif;
	}

	public void setExif(String exif) {
		this.exif = exif;
	}
	
	
}
