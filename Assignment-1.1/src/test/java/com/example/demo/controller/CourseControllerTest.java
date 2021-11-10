package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entities.Course;
import com.example.demo.repository.CourseRepository;

@ExtendWith(MockitoExtension.class)
public class CourseControllerTest {
	
	@Mock
	CourseRepository courseRepo;
	
	@Spy
	@InjectMocks
	CourseController courseController;
	
	Course course = new Course();
	
	List<Course> courseList = new ArrayList<>();
	
	@Test
	void addCountryTest() {
		course.setName("test");
		when(courseRepo.save(Mockito.any())).thenReturn(course);
		Course response = courseController.addCourse(course);
		assertEquals("test", response.getName());
	}
	
	@Test
	void updateCountryTest() {
		course.setName("test");
		when(courseRepo.save(Mockito.any())).thenReturn(course);
		Course response = courseController.updateCourse(course);
		assertEquals("test", response.getName());
	}
	
	@Test
	void getCountryTest() {
		course.setName("test");
		courseList.add(course);
		when(courseRepo.findAll()).thenReturn(courseList);
		List<Course> response = courseController.getCourses();
		assertEquals(1, response.size());
	}
	
	@Test
	void deleteCountryTest() {
		doNothing().when(courseRepo).deleteById(Mockito.anyLong());
		Boolean response = courseController.deleteCourse(1L);
		assertTrue(response);
	}

}
