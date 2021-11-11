package com.example.demo.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.retry.annotation.Recover;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
public class CircuitBreakerController {
	
	private static final Logger LOG = Logger.getLogger(CircuitBreakerController.class.getName());
	
	@Autowired
	TeacherController teacherController;
	
	@DeleteMapping("/circuit/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value="JWT") })
	@HystrixCommand(fallbackMethod = "fallBack")
	public String getTeachersWithCircuitBreaker(@RequestParam Long id){
		LOG.log(Level.INFO, "Get Teachers With CircuitBreaker called...");
		try {
			teacherController.deleteTeacher(id);
			return "Deleted Successfully";
		} catch (Exception e) {
			throw new EmptyResultDataAccessException(0);
		}
	}

	@Recover
	public String fallBack(Long id) {
		return "Teacher with Id - " + id + " doen't exist.";
	}

}
