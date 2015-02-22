package com.service.upemgur.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.resthub.common.service.CrudServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.service.upemgur.model.Image;
import com.service.upemgur.model.User;
import com.service.upemgur.repository.ImageRepository;
import com.service.upemgur.service.IImageService;

@Named("imageService")
public class ImageServiceImpl extends
		CrudServiceImpl<Image, Integer, ImageRepository> implements
		IImageService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Inject
	@Named("imageRepository")
	private ImageRepository imageRepository;

	@Override
	public Image getImage(int id) {
		// TODO Auto-generated method stub
		final Image image = imageRepository.findById(id);
		return image;
	}

	@Override
	public void createImage(Image image) {
		// TODO Auto-generated method stub

		imageRepository.save(image);
	}

	

	@Override
	public void deleteImage(Image image) {
		// TODO Auto-generated method stub
		imageRepository.delete(image);
		imageRepository.flush();
	}

	@Override
	public Page<Image> getImageUser(User user) {
		// TODO Auto-generated method stub
		return imageRepository.findAllByUser(user, null);
	}

	@Override
	public Page<Image> getAllImages(Pageable pageable) {
		// TODO Auto-generated method stub
		return imageRepository.findAll(pageable);
		
	}

	@Override
	public Page<Image> getPublicImages() {
		// TODO Auto-generated method stub
		return imageRepository.findAllPublicImage(null);
	}

	@Override
	public void privateImage(Image image) {
		image.setPublicImage(0);
		imageRepository.save(image);	
		imageRepository.flush();
	}
	
	@Override
	public void shareImage(Image image) {
		// TODO Auto-generated method stub

		image.setPublicImage(1);
		imageRepository.save(image);	
		imageRepository.flush();
	}
	


}
