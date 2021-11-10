package com.prop.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.prop.models.Teacher;

@FeignClient(name="FeignClient", url = "http://localhost:8080" )
public interface FeignProxy {
	
	@GetMapping("/teacher")
	public List<Teacher> getAllTeachers(@RequestHeader("Authorization") String authorization);

}
