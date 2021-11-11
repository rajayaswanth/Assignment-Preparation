package com.example.demo.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Course;
import com.example.demo.repository.CourseRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
public class CourseController {
	
	private static final Logger LOG = Logger.getLogger(CourseController.class.getName());
	
	@Autowired
	CourseRepository courseRepo;
	
	@PostMapping("/course")
	@ApiOperation(value = "", authorizations = { @Authorization(value="JWT") })
	Course addCourse(@RequestBody Course course) {
		LOG.log(Level.INFO, "Add Course api is called...");
		return courseRepo.save(course);
	}
	
	@PutMapping("/course")
	@ApiOperation(value = "", authorizations = { @Authorization(value="JWT") })
	Course updateCourse(@RequestBody Course course) {
		LOG.log(Level.INFO, "Update Course api is called...");
		return courseRepo.save(course);
	}
	
	@GetMapping("/course")
	@ApiOperation(value = "", authorizations = { @Authorization(value="JWT") })
	List<Course> getCourses() {
		LOG.log(Level.INFO, "Get all Courses api is called...");
		return courseRepo.findAll();
	}
	
	@DeleteMapping("/course/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value="JWT") })
	Boolean deleteCourse(@PathVariable Long id) {
		LOG.log(Level.INFO, "Delete Course api is called...");
		courseRepo.deleteById(id);
		return true;
	}

}
