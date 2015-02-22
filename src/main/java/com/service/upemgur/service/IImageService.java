package com.service.upemgur.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import com.service.upemgur.model.Image;
import com.service.upemgur.model.User;

public interface IImageService {
	
	public Image getImage(int id);
	public void createImage(Image image);
	public void shareImage(Image image);
	public void deleteImage(Image image);
	public Page<Image> getImageUser(User user);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Page<Image> getAllImages(Pageable pageable);
	
	public Page<Image> getPublicImages();
	public void privateImage(Image image);
	
}
