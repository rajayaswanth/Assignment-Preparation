package com.example.demo.controller;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
public class TeacherController {
	
	private final Bucket bucket;

    public TeacherController() {
        Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofHours(1)));
        this.bucket = Bucket4j.builder()
            .addLimit(limit)
            .build();
    }
	
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
	@Transactional(timeout = 1)
	@ApiOperation(value = "", authorizations = { @Authorization(value="JWT") })
	ResponseEntity<?> getTeachers() {
		if(bucket.tryConsume(1)) {
			return new ResponseEntity<List<Teacher>>(teacherRepo.findAll(), HttpStatus.OK);
		}
		return new ResponseEntity<>("Too many requests try after 1 hour...", HttpStatus.TOO_MANY_REQUESTS);
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
