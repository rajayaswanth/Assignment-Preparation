package com.example.demo.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Teacher;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
public class RateLimiterController {
	
	private static final Logger LOG = Logger.getLogger(RateLimiterController.class.getName());
	
	@Autowired
	TeacherController teacherController;
	
	@GetMapping("/rate/{noOfHits}")
	@ApiOperation(value = "", authorizations = { @Authorization(value="JWT") })
	ResponseEntity<?> getTeachers(@RequestParam int noOfHits) {
		LOG.log(Level.INFO, "rate limiter api is called...");
		if(noOfHits > 10) {
			return new ResponseEntity<>("Bucket size is only 10", HttpStatus.BAD_REQUEST);
		} else {
			ResponseEntity<?> list = new ResponseEntity<List<Teacher>>(HttpStatus.OK);
			for (int i = 0; i < noOfHits; i++) {
				list = teacherController.getTeachers();
			}
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

}
