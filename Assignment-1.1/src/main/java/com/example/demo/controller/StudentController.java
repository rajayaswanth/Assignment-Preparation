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

import com.example.demo.entities.Student;
import com.example.demo.repository.StudentRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
public class StudentController {
	
	private static final Logger LOG = Logger.getLogger(StudentController.class.getName());
	
	@Autowired
	StudentRepository studentRepo;
	
	@PostMapping("/student")
	@ApiOperation(value = "", authorizations = { @Authorization(value="JWT") })
	Student addStudent(@RequestBody Student student) {
		LOG.log(Level.INFO, "Add Student api is called...");
		return studentRepo.save(student);
	}
	
	@PutMapping("/student")
	@ApiOperation(value = "", authorizations = { @Authorization(value="JWT") })
	Student updateStudent(@RequestBody Student student) {
		LOG.log(Level.INFO, "Update Student api is called...");
		return studentRepo.save(student);
	}
	
	@GetMapping("/student")
	@ApiOperation(value = "", authorizations = { @Authorization(value="JWT") })
	List<Student> getStudents() {
		LOG.log(Level.INFO, "Get all Students api is called...");
		return studentRepo.findAll();
	}
	
	@DeleteMapping("/student/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value="JWT") })
	Boolean deleteStudent(@PathVariable Long id) {
		LOG.log(Level.INFO, "Delete Student api is called...");
		studentRepo.deleteById(id);
		return true;
	}

}
