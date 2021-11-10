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

import com.example.demo.entities.Student;
import com.example.demo.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
	
	@Mock
	StudentRepository studentRepo;
	
	@Spy
	@InjectMocks
	StudentController studentController;
	
	Student student = new Student();
	
	List<Student> studentList = new ArrayList<>();
	
	@Test
	void addStudentTest() {
		student.setName("test");
		when(studentRepo.save(Mockito.any())).thenReturn(student);
		Student response = studentController.addStudent(student);
		assertEquals("test", response.getName());
	}
	
	@Test
	void updateStudentTest() {
		student.setName("test");
		when(studentRepo.save(Mockito.any())).thenReturn(student);
		Student response = studentController.updateStudent(student);
		assertEquals("test", response.getName());
	}
	
	@Test
	void getStudentTest() {
		student.setName("test");
		studentList.add(student);
		when(studentRepo.findAll()).thenReturn(studentList);
		List<Student> response = studentController.getStudents();
		assertEquals(1, response.size());
	}
	
	@Test
	void deleteStudentTest() {
		doNothing().when(studentRepo).deleteById(Mockito.anyLong());
		Boolean response = studentController.deleteStudent(1L);
		assertTrue(response);
	}

}
