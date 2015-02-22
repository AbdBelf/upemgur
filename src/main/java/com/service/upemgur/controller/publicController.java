package com.service.upemgur.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.service.upemgur.model.Image;
import com.service.upemgur.model.User;
import com.service.upemgur.service.IImageService;
import com.service.upemgur.service.IUserService;

@Controller
@RequestMapping(value = "/public/upemgur")
public class publicController {

	@Inject
	@Named("imageService")
	private IImageService imageService;

	@Inject
	@Named("userService")
	private IUserService userService;
	

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView publicPage(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView();

		model.setViewName("public");
		
		return model;

	}
	
	@RequestMapping(value = "getPublicImages", method = RequestMethod.GET)
	@ResponseBody
	public Page<Image> getPublicImages(HttpServletRequest request,
			HttpServletResponse response) {

		return imageService.getPublicImages();

	}
	


	@RequestMapping(value = "downloadImage/{imageId}", method = RequestMethod.GET)
	@ResponseBody
	public String downloadImage(@PathVariable int imageId, HttpServletRequest request, HttpServletResponse response) {

		Image imageDB = imageService.getImage(imageId);
		String content = "";
		if (imageDB == null) {
			return null;
		}
		

	   
		byte[] base64;
		try {
	        InputStream is = new FileInputStream(imageDB.getURL());

			base64 = IOUtils.toByteArray(is);
			content = Base64.encodeBase64String(base64);
			//content = new String(base64);
			
			return content;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	   

	    
		return null;
		

	}

	


	

}
