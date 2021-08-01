package com.iktpreobuka.EDnevnik.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table (name = "Subjects")
public class SubjectEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "teacherEntity")
	private TeacherEntity teacherEntity;
	
	@JsonBackReference
	@OneToMany(mappedBy = "subjectEntity", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	protected List<Grade_OcenaEntity> ocene = new ArrayList<>();
	
	@JsonManagedReference
	@ManyToMany(mappedBy = "predmeti")
    private Set<TeacherEntity> teachers = new HashSet<>();
	
	@JsonManagedReference
	@ManyToMany(mappedBy = "predmeti")
    private Set<Grade_RazredEntity> razred = new HashSet<>();
	
    @JsonBackReference
	@OneToMany(mappedBy = "subjectEntity", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	protected List<SubjectEntity> subjectEntity = new ArrayList<>();
	
	
	public List<Grade_OcenaEntity> getOcene() {
		return ocene;
	}

	public void setOcene(List<Grade_OcenaEntity> ocene) {
		this.ocene = ocene;
	}

	public Set<TeacherEntity> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<TeacherEntity> teachers) {
		this.teachers = teachers;
	}

	@Column(name = "name", nullable = false)
	@NotBlank(message = "Name must be provided.")
	@Size(min = 5, max = 25, message = "Subject name must be between {min} and {max} characters long.")
	private String name;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public SubjectEntity () {
		super();
	}

	public TeacherEntity getTeacherEntity() {
		return teacherEntity;
	}

	public void setTeacherEntity(TeacherEntity teacherEntity) {
		this.teacherEntity = teacherEntity;
	}

	public Set<Grade_RazredEntity> getRazred() {
		return razred;
	}

	public void setRazred(Set<Grade_RazredEntity> razred) {
		this.razred = razred;
	}

	
}
