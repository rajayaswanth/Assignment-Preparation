package com.example.demo.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	private static final Logger LOG = Logger.getLogger(UserController.class.getName());
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PasswordEncoder encoder;

	/**
	 * Creates user
	 * @param userRequest
	 * @return
	 */
	@PostMapping(value = "/add")
	public User addUser(@RequestBody @Nullable User userRequest) {
		LOG.log(Level.INFO, "Add User api is called...");
		userRequest.setPassword(encoder.encode(userRequest.getPassword()));
		return userRepo.save(userRequest);
	}
	
	/**
	 * Updates User
	 * @param userRequest
	 * @return
	 */
	@PutMapping(value = "/update")
	@ApiOperation(value = "", authorizations = { @Authorization(value="JWT") })
	public User updateUser(@RequestBody User userRequest) {
		LOG.log(Level.INFO, "Update User api is called...");
		userRequest.setPassword(encoder.encode(userRequest.getPassword()));
		return userRepo.save(userRequest);
	}
	
	/**
	 * Gets users list
	 * @return
	 */
	@GetMapping(value = "/getAll")
	@ApiOperation(value = "", authorizations = { @Authorization(value="JWT") })
	public List<User> getUsers() {
		LOG.log(Level.INFO, "Get all Users api is called...");
		return userRepo.findAll();
	}
	
	/**
	 * Deletes user by ID
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value="JWT") })
	public String deleteUser(@PathVariable Long id) {
		LOG.log(Level.INFO, "Delete User api is called...");
		userRepo.deleteById(id);
		return "deleted successfully";
	}

}