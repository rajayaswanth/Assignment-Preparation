package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	
	@Mock
	UserRepository userReo;
	
	@Mock
	PasswordEncoder encoder;
	
	@Spy
	@InjectMocks
	UserController userController;
	
	User user = new User();
	
	List<User> userList = new ArrayList<>();
	
	@Test
	void addUserTest() {
		user.setName("test");
		user.setPassword("test");
		when(userReo.save(Mockito.any())).thenReturn(user);
		User response = userController.addUser(user);
		assertEquals("test", response.getName());
	}
	
	@Test
	void updateUserTest() {
		user.setName("test");
		user.setPassword("test");
		when(userReo.save(Mockito.any())).thenReturn(user);
		User response = userController.updateUser(user);
		assertEquals("test", response.getName());
	}
	
	@Test
	void getUserTest() {
		user.setName("test");
		userList.add(user);
		when(userReo.findAll()).thenReturn(userList);
		List<User> response = userController.getUsers();
		assertEquals(1, response.size());
	}
	
	@Test
	void deleteUserTest() {
		doNothing().when(userReo).deleteById(Mockito.anyLong());
		String response = userController.deleteUser(1L);
		assertEquals("deleted successfully", response);
	}

}
