package com.example.demo.config;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.boot.context.properties.bind.BindException;

import com.example.demo.DTO.CourseMapperDTO;

public class CourseSetMapper implements FieldSetMapper<CourseMapperDTO> {
	
	@Override
	public CourseMapperDTO mapFieldSet(FieldSet fieldSet) throws BindException {
		return new CourseMapperDTO(fieldSet.readString("name"),
				fieldSet.readLong("teacher_id"));
	}

}
