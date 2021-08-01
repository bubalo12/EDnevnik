package com.iktpreobuka.EDnevnik.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name =  "report")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ReportEntity {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "reprot_id")
	private Integer id;
	
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "studentEntity")
	private StudentEntity studentEntity;
	
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "subjectEntity")
	private SubjectEntity subjectEntity;
	
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "teacherEntity")
	private TeacherEntity teacherEntity;
	
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "razredEntity")
	private Grade_RazredEntity razredEntity;
	
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ocenaEntity")
	private Grade_OcenaEntity ocenaEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StudentEntity getStudentEntity() {
		return studentEntity;
	}

	public void setStudentEntity(StudentEntity studentEntity) {
		this.studentEntity = studentEntity;
	}

	public SubjectEntity getSubjectEntity() {
		return subjectEntity;
	}

	public void setSubjectEntity(SubjectEntity subjectEntity) {
		this.subjectEntity = subjectEntity;
	}

	public TeacherEntity getTeacherEntity() {
		return teacherEntity;
	}

	public void setTeacherEntity(TeacherEntity teacherEntity) {
		this.teacherEntity = teacherEntity;
	}

	public Grade_RazredEntity getRazredEntity() {
		return razredEntity;
	}

	public void setRazredEntity(Grade_RazredEntity razredEntity) {
		this.razredEntity = razredEntity;
	}

	public Grade_OcenaEntity getOcenaEntity() {
		return ocenaEntity;
	}

	public void setOcenaEntity(Grade_OcenaEntity ocenaEntity) {
		this.ocenaEntity = ocenaEntity;
	}
	
	

}
