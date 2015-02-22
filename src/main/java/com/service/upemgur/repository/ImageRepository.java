package com.service.upemgur.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.service.upemgur.model.Image;
import com.service.upemgur.model.User;

public interface ImageRepository  extends JpaRepository<Image, Integer> , JpaSpecificationExecutor<Image>  {

	
	Image findById(@Param(value = "id") int id);
	
	@Query("SELECT i FROM Image i " + "WHERE (i.user = :user)")
	public Page<Image> findAllByUser(@Param(value = "user") User user, Pageable pageable);
	
	@Query("SELECT i FROM Image i " + "WHERE (i.publicImage = 1)")
	public Page<Image> findAllPublicImage(Pageable pageable);


}
