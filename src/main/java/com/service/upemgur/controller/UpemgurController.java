package com.service.upemgur.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import com.service.upemgur.model.Image;
import com.service.upemgur.model.User;
import com.service.upemgur.service.IImageService;
import com.service.upemgur.service.IUserService;
import com.service.upemgur.utils.Utils;

@Controller
@RequestMapping(value = "/upemgur")
public class UpemgurController {

	@Inject
	@Named("imageService")
	private IImageService imageService;

	@Inject
	@Named("userService")
	private IUserService userService;

	@Value("${webservices.uploadURL}")
	private String uploadURL;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UpemgurController.class);

	@RequestMapping(value = "admin", method = RequestMethod.GET)
	public ModelAndView admin(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView();

		model.setViewName("admin");

		return model;

	}

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView();

		model.setViewName("index");

		User user = userService.getUser();

		if (user != null) {

			model.addObject("connectedUser", user.getUsername());
			model.addObject("isAdmin", Utils.ifUserAdmin(request));

		}

		return model;

	}

	@RequestMapping(value = "getAllImages", method = RequestMethod.GET)
	@ResponseBody
	public Page<Image> getAllImages(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false) Pageable pageable) {

		return imageService.getAllImages(pageable);

	}

	@RequestMapping(value = "getImageUser", method = RequestMethod.GET)
	@ResponseBody
	public Page<Image> getImageUser(HttpServletRequest request,
			HttpServletResponse response) {

		User user = userService.getUser();
		return imageService.getImageUser(user);

	}

	@RequestMapping(value = "deleteImage/{imageId}", method = RequestMethod.GET)
	@ResponseBody
	public void deleteImage(@PathVariable int imageId,
			HttpServletRequest request, HttpServletResponse response) {

		User user = userService.getUser();
		Image imageDB = imageService.getImage(imageId);

		if (imageDB == null) {
			return;
		}
		/**
		 * Check if the user image equals username or it's an Admin user
		 */
		if ((imageDB.getUser().getUsername().equals(user.getUsername()))
				|| Utils.ifUserAdmin(request)) {
			imageService.deleteImage(imageDB);
		}

	}

	@RequestMapping(value = "shareImage/{imageId}", method = RequestMethod.GET)
	@ResponseBody
	public void shareImage(@PathVariable int imageId,
			HttpServletRequest request, HttpServletResponse response, @RequestParam(required = true) boolean share) {

		Image imageDB = imageService.getImage(imageId);
		if (imageDB == null) {
			return;
		}
		if (share == true){
			imageService.shareImage(imageDB);
		}else{
			imageService.privateImage(imageDB);
		}

	}

	@RequestMapping(value = "createUser", method = RequestMethod.POST)
	@ResponseBody
	public User createUser(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = true) String email,
			@RequestParam(required = true) String password) {

		User user = userService.findUser(email);

		if (user != null) {
			throw new EntityExistsException();
		}

		user = new User();
		user.setEnabled(1);
		user.setPassword(password);
		user.setUsername(email);

		userService.createUser(user);
		
		return user;

	}

	@RequestMapping(value = "uploadImage", method = RequestMethod.POST)
	@ResponseBody
	public void uploadImage(HttpServletRequest request,
			@RequestParam(required = false) List<CommonsMultipartFile> files) {

		if (files.isEmpty()
				|| !files.get(0).getContentType().equals("image/jpeg")) {
			return;
		}

		InputStream is = null;
		try {
			is = files.get(0).getInputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
		}

		ArrayList<String> EXIF = new ArrayList<String>();

		try {
			Parser parser = new AutoDetectParser();
			ContentHandler contentHandler = new BodyContentHandler();
			Metadata metadata = new Metadata();

			parser.parse(is, contentHandler, metadata, new ParseContext());

			for (String name : metadata.names()) {
				String value = metadata.get(name);
				EXIF.add(name + " : " + value);
			}

			File dest = new File(uploadURL + files.get(0).getOriginalFilename());
			files.get(0).transferTo(dest);

			User user = userService.getUser();

			Image image = new Image();
			image.setPublicImage(0);
			image.setURL(dest.getAbsolutePath());
			image.setExif(EXIF.toString());
			image.setUser(user);
			imageService.createImage(image);

		} catch (IOException e) {
			// TODO Auto-generated catch block
		} catch (SAXException e) {
			// TODO Auto-generated catch block

		} catch (TikaException e) {
			// TODO Auto-generated catch block
		}

		return;

	}

}
