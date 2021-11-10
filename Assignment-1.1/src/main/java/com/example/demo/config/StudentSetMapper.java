package com.example.demo.config;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.boot.context.properties.bind.BindException;

import com.example.demo.DTO.StudentMapperDTO;

public class StudentSetMapper implements FieldSetMapper<StudentMapperDTO> {
	
	@Override
	public StudentMapperDTO mapFieldSet(FieldSet fieldSet) throws BindException {
		return new StudentMapperDTO(
				fieldSet.readString("name"),
				fieldSet.readString("email"),
				fieldSet.readLong("course_id"));
	}

}
