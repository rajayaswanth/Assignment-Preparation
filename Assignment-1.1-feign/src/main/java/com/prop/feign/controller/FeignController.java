package com.prop.feign.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.prop.feign.FeignProxy;
import com.prop.models.Teacher;

@RestController
public class FeignController {

	@Autowired
	FeignProxy proxyService;

	@GetMapping("/feign/teacher")
	public List<Teacher> getTeachers(@RequestHeader("Authorization") String authorization){
		return proxyService.getAllTeachers(authorization);
	}

}
