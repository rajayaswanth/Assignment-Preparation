package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Teacher;
import com.example.demo.repository.TeacherRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
public class TeacherController {
	
	@Autowired
	TeacherRepository teacherRepo;
	
	@PostMapping("/teacher")
	@ApiOperation(value = "", authorizations = { @Authorization(value="JWT") })
	Teacher addTeacher(@RequestBody Teacher teacher) {
		return teacherRepo.save(teacher);
	}
	
	@PutMapping("/teacher")
	@ApiOperation(value = "", authorizations = { @Authorization(value="JWT") })
	Teacher updateTeacher(@RequestBody Teacher teacher) {
		return teacherRepo.save(teacher);
	}
	
	@GetMapping("/teacher")
	@Transactional(propagation = Propagation.MANDATORY, timeoutString = "0.1")
	@ApiOperation(value = "", authorizations = { @Authorization(value="JWT") })
	List<Teacher> getTeachers() {
		return teacherRepo.findAll();
	}
	
	@DeleteMapping("/teacher/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value="JWT") })
	Boolean deleteTeacher(@PathVariable Long id) {
		try {
			teacherRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			throw new EmptyResultDataAccessException(0);
		}
	}

}
