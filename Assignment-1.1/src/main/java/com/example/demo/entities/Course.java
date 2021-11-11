package com.example.demo.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "courses")
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Course implements Serializable {
	
	private static final long serialVersionUID = 662575930788082079L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@GenericGenerator(strategy = "native", name = "native")
	private Long id;
	
	private String name;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "course", orphanRemoval = true)
	private List<Student> students;
	
	@ManyToOne
	private Teacher teacher;

}
