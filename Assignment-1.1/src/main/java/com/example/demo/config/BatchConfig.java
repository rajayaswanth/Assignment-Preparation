package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.example.demo.DTO.CourseMapperDTO;
import com.example.demo.DTO.StudentMapperDTO;
import com.example.demo.entities.Teacher;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	@Value("${file.input.teacher}")
	private String teacherInputFile;

	@Value("${file.input.course}")
	private String courseInputFile;

	@Value("${file.input.student}")
	private String studentInputFile;

	@Bean
	public FlatFileItemReader<Teacher> teacherItemReader() {
		FlatFileItemReader<Teacher> reader = new FlatFileItemReader<>();
		reader.setLinesToSkip(1);
		reader.setResource(new ClassPathResource(teacherInputFile));

		DefaultLineMapper<Teacher> customerLineMapper = new DefaultLineMapper<>();

		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setNames(new String[] {"name"});
		tokenizer.setDelimiter(",");
		
		customerLineMapper.setLineTokenizer(tokenizer);
		customerLineMapper.setFieldSetMapper(new TeacherSetMapper());
		customerLineMapper.afterPropertiesSet();
		reader.setLineMapper(customerLineMapper);
		return reader;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public JdbcBatchItemWriter<Teacher> teacherItemWriter() {
		JdbcBatchItemWriter<Teacher> itemWriter = new JdbcBatchItemWriter<>();

		itemWriter.setDataSource(this.dataSource);
		itemWriter.setSql("INSERT INTO TEACHERS(name) VALUES (:name)");
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
		itemWriter.afterPropertiesSet();

		return itemWriter;
	}
	
	@Bean
	public FlatFileItemReader<CourseMapperDTO> courseItemReader() {
		FlatFileItemReader<CourseMapperDTO> reader = new FlatFileItemReader<>();
		reader.setLinesToSkip(1);
		reader.setResource(new ClassPathResource(courseInputFile));

		DefaultLineMapper<CourseMapperDTO> customerLineMapper = new DefaultLineMapper<>();

		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setNames(new String[] {"name", "teacher_id"});
		tokenizer.setDelimiter(",");
		
		customerLineMapper.setLineTokenizer(tokenizer);
		customerLineMapper.setFieldSetMapper(new CourseSetMapper());
		customerLineMapper.afterPropertiesSet();
		reader.setLineMapper(customerLineMapper);
		return reader;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public JdbcBatchItemWriter<CourseMapperDTO> courseItemWriter() {
		JdbcBatchItemWriter<CourseMapperDTO> itemWriter = new JdbcBatchItemWriter<>();

		itemWriter.setDataSource(this.dataSource);
		itemWriter.setSql("INSERT INTO COURSES(name, teacher_id) VALUES (:name, :teacherId)");
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
		itemWriter.afterPropertiesSet();

		return itemWriter;
	}
	
	@Bean
	public FlatFileItemReader<StudentMapperDTO> studentItemReader() {
		FlatFileItemReader<StudentMapperDTO> reader = new FlatFileItemReader<>();
		reader.setLinesToSkip(1);
		reader.setResource(new ClassPathResource(studentInputFile));

		DefaultLineMapper<StudentMapperDTO> customerLineMapper = new DefaultLineMapper<>();

		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setNames(new String[] {"name", "email", "course_id"});
		tokenizer.setDelimiter(",");
		
		customerLineMapper.setLineTokenizer(tokenizer);
		customerLineMapper.setFieldSetMapper(new StudentSetMapper());
		customerLineMapper.afterPropertiesSet();
		reader.setLineMapper(customerLineMapper);
		return reader;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public JdbcBatchItemWriter<StudentMapperDTO> studentItemWriter() {
		JdbcBatchItemWriter<StudentMapperDTO> itemWriter = new JdbcBatchItemWriter<>();

		itemWriter.setDataSource(this.dataSource);
		itemWriter.setSql("INSERT INTO STUDENTS(name, email, course_id) VALUES (:name, :email, :courseId)");
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
		itemWriter.afterPropertiesSet();

		return itemWriter;
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<Teacher, Teacher>chunk(10)
				.reader(teacherItemReader())
				.writer(teacherItemWriter())
//				.allowStartIfComplete(true)
				.build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2")
				.<CourseMapperDTO, CourseMapperDTO>chunk(10)
				.reader(courseItemReader())
				.writer(courseItemWriter())
//				.allowStartIfComplete(true)
				.build();
	}

	@Bean
	public Step step3() {
		return stepBuilderFactory.get("step3")
				.<StudentMapperDTO, StudentMapperDTO>chunk(10)
				.reader(studentItemReader())
				.writer(studentItemWriter())
//				.allowStartIfComplete(true)
				.build();
	}

	@Bean
	public Job job() {
		return jobBuilderFactory.get("job")
				.start(step1())
				.next(step2())
				.next(step3())
				.build();
	}

}
