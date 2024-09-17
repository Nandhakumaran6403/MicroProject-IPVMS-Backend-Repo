package com.visitor.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.visitor.application.model.AdministrativeUser;
import com.visitor.application.service.AdministrativeUserService;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin-users")
public class AdministrativeUserController {

	@Autowired
	private AdministrativeUserService administrativeUserService;

	@PostMapping
	public String addAdministrativeUser(@RequestParam("userName") String userName, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("role") String role,
			@RequestParam(value = "profileImageBlob", required = false) MultipartFile profileImageBlob) {

		try {
			byte[] imageBytes = null;
			if (profileImageBlob != null && !profileImageBlob.isEmpty()) {
				if (profileImageBlob.getSize() > 10 * 1024 * 1024) {
					return "File size exceeds the maximum limit of 10MB";
				}
				imageBytes = profileImageBlob.getBytes();
			}

			AdministrativeUser administrativeUser = new AdministrativeUser();
			administrativeUser.setUserName(userName);
			administrativeUser.setEmail(email);
			administrativeUser.setPassword(password);
			administrativeUser.setRole(role);
			administrativeUser.setLastLoginDate(new Timestamp(System.currentTimeMillis()));
			administrativeUser.setProfileImageBlob(imageBytes);

			administrativeUserService.saveAdministrativeUser(administrativeUser);
			return "AddSuccess";

		} catch (IOException e) {
			return "AddFailure";
		}
	}

	@GetMapping("/all")
	public List<AdministrativeUser> viewAllAdministrativeUsers() {
		return administrativeUserService.getAllAdministrativeUsers();
	}

	@GetMapping("/{id}")
	public AdministrativeUser getAdministrativeUserById(@PathVariable("id") int id) {
		return administrativeUserService.getAdministrativeUserById(id);
	}

	@DeleteMapping("/{id}")
	public String deleteAdministrativeUserById(@PathVariable("id") int id) {
		try {
			administrativeUserService.deleteAdministrativeUser(id);
			return "DeleteSuccess";
		} catch (Exception e) {
			return "DeleteFailure";
		}
	}

	@PutMapping("/{id}")
	public String updateAdministrativeUser(@PathVariable int id, @RequestParam("userName") String userName,
			@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("role") String role,
			@RequestParam(value = "profileImageBlob", required = false) MultipartFile profileImageBlob) {

		String msg;
		try {
			AdministrativeUser existingAdministrativeUser = administrativeUserService.getAdministrativeUserById(id);
			if (existingAdministrativeUser == null) {
				return "AdministrativeUser not found";
			}

			if (profileImageBlob != null && !profileImageBlob.isEmpty()) {
				if (profileImageBlob.getSize() > 10 * 1024 * 1024) {
					return "File size exceeds the maximum limit of 10MB";
				}
				existingAdministrativeUser.setProfileImageBlob(profileImageBlob.getBytes());
			}

			existingAdministrativeUser.setUserName(userName);
			existingAdministrativeUser.setEmail(email);
			existingAdministrativeUser.setPassword(password);
			existingAdministrativeUser.setRole(role);

			administrativeUserService.updateAdministrativeUser(id, existingAdministrativeUser);

			msg = "UpdateSuccess";
		} catch (IOException e) {
			msg = "UpdateFailure";
		}
		return msg;
	}

	@PatchMapping("/lastlogin/{id}")
	public AdministrativeUser updateLastLoginDate(@PathVariable("id") int id) {
		AdministrativeUser user = administrativeUserService.getAdministrativeUserById(id);
		if (user != null) {
			user.setLastLoginDate(new Date());
			return administrativeUserService.updateAdministrativeUser(id, user);
		}
		return null;
	}
}
