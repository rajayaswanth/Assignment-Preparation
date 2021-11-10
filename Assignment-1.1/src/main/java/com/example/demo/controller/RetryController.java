package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
public class RetryController {
	
	@Autowired
	TeacherController teacherController;
	
	@DeleteMapping("/retry/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value="JWT") })
	@Retryable(value = {EmptyResultDataAccessException.class}, maxAttempts = 5, backoff = @Backoff(delay = 1000))
	public String getTeachersWithRetry(@RequestParam Long id){
		try {
			System.out.println("Retrying...");
			teacherController.deleteTeacher(id);
			return "Deleted Successfully";
		} catch (Exception e) {
			throw new EmptyResultDataAccessException(0);
		}
	}

	@Recover
	public String fallBack(EmptyResultDataAccessException ex, Long id) {
		return "Teacher with Id - " + id + " doen't exist.";
	}

}
