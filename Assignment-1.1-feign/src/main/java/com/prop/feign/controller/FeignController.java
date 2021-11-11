package com.prop.feign.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.prop.feign.FeignProxy;
import com.prop.models.Teacher;

@RestController
public class FeignController {
	
	private static final Logger LOG = Logger.getLogger(FeignController.class.getName());
	
	@Autowired
	FeignProxy proxyService;

	@GetMapping("/feign/teacher")
	public List<Teacher> getTeachers(@RequestHeader("Authorization") String authorization){
		LOG.log(Level.INFO, "Get Teachers With feign client called...");
		return proxyService.getAllTeachers(authorization);
	}

}
