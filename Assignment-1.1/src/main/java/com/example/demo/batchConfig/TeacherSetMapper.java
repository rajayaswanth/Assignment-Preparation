package com.example.demo.batchConfig;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.boot.context.properties.bind.BindException;

import com.example.demo.entities.Teacher;

public class TeacherSetMapper implements FieldSetMapper<Teacher> {
	
	@Override
	public Teacher mapFieldSet(FieldSet fieldSet) throws BindException {
		return new Teacher(0L,
				fieldSet.readString("name"),
				null);
	}

}
