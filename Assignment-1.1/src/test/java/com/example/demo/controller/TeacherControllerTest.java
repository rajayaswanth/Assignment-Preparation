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

import com.example.demo.entities.Teacher;
import com.example.demo.repository.TeacherRepository;

@ExtendWith(MockitoExtension.class)
public class TeacherControllerTest {
	
	@Mock
	TeacherRepository teacherRepo;
	
	@Spy
	@InjectMocks
	TeacherController teacherController;
	
	Teacher teacher = new Teacher();
	
	List<Teacher> teacherList = new ArrayList<>();
	
	@Test
	void addTeacherTest() {
		teacher.setName("test");
		when(teacherRepo.save(Mockito.any())).thenReturn(teacher);
		Teacher response = teacherController.addTeacher(teacher);
		assertEquals("test", response.getName());
	}
	
	@Test
	void updateTeacherTest() {
		teacher.setName("test");
		when(teacherRepo.save(Mockito.any())).thenReturn(teacher);
		Teacher response = teacherController.updateTeacher(teacher);
		assertEquals("test", response.getName());
	}
	
	@Test
	void getTeacherTest() {
		teacher.setName("test");
		teacherList.add(teacher);
		when(teacherRepo.findAll()).thenReturn(teacherList);
		List<Teacher> response = teacherController.getTeachers();
		assertEquals(1, response.size());
	}
	
	@Test
	void deleteStudentTest() {
		doNothing().when(teacherRepo).deleteById(Mockito.anyLong());
		Boolean response = teacherController.deleteTeacher(1L);
		assertTrue(response);
	}

}
