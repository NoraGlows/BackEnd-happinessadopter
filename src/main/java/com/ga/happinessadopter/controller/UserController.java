package com.ga.happinessadopter.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ga.happinessadopter.model.User;
import com.ga.happinessadopter.model.JwtResponse;
import com.ga.happinessadopter.config.JwtUtil;
import com.ga.happinessadopter.dao.UserDao;

@RestController
public class UserController {

	@Autowired
	private UserDao dao;

	// To post the registration form
	@PostMapping("/user/registration")
	public HashMap<String, String> registration(@RequestBody User user) {
		HashMap<String, String> response = new HashMap<String, String>();
		var it = dao.findAll();
//check if user already in database
		for (User dbUser : it) {
			if (dbUser.getEmailAddress().equals(user.getEmailAddress())) {
				response.put("message", "User already exists");
				return response;
			}
		}

		// Password Encryption
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		String newPassword = bCrypt.encode(user.getPassword());
		user.setPassword(newPassword);
		dao.save(user);
		response.put("message", "User registered successfully");
		return response;

	}

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	UserDetailsService userDetailsService;

	@PostMapping("/user/authenticate")
	//? Optional entity it's can be anything
	public ResponseEntity<?> authenticate(@RequestBody User user) {
        //authenticate username and password
		// if user authenticate do this
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmailAddress(), user.getPassword()));
			// if user not authenticate do this
		} catch (BadCredentialsException e) {
			String res = "Incorrect username or password";
			return ResponseEntity.ok(res);
		}
		// if user authenticate it's will complete for this code to generate jwtToken
		// to bring user detail object
		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmailAddress());
		String jwtToken = jwtUtil.generateToken(userDetails);
		System.out.println(jwtToken);
		return ResponseEntity.ok(new JwtResponse(jwtToken));

	}
	@GetMapping("/user/profile")
	public User getProfile(@RequestParam String email) {
		User user = dao.findByEmailAddress(email);

		return user;
	}
	@PutMapping("/user/edit")
	public HashMap<String, String> editUser(@RequestBody User user, @RequestParam String email) {
		HashMap<String, String> response = new HashMap<String, String>();
		
		// Check the edited email is already in db or not
		var it = dao.findAll();
		for (User dbUser : it) {
			if (dbUser.getEmailAddress().equals(user.getEmailAddress()) && !email.equals(user.getEmailAddress())) {

				response.put("message", "this email already used");
				return response;
			}
		}

		dao.save(user);
		response.put("message", "User edited successfully!");
		return response;

	}
	@PutMapping("/user/changePassword")
	public boolean changePassword(@RequestBody User user, @RequestParam String currentPassword) {
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

		User matchedUser = dao.findByEmailAddress(user.getEmailAddress());
		if (matchedUser != null) {
			if (bCrypt.matches(currentPassword, matchedUser.getPassword())) {

				String newPassword = bCrypt.encode(user.getPassword());
				user.setPassword(newPassword);

				dao.save(user);
				return true;
			} else {
				System.err.println("Passwords doesn't matches");
				return false;
			}
		} else {
			System.err.println("User not found!, matchedUser is null ");
			return false;

		}

	}
}
