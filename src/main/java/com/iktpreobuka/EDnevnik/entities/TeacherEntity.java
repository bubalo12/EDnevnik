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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name = "Teachers")
public class TeacherEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer teacherId;
	
	@Column(name = "username", nullable = false, unique = true)
	@NotBlank(message = "Username must be provided.")
	@Size(min = 5, max = 20, message = "Username must be between {min} and {max} characters long.")
	private String username;
	
	@JsonIgnore
	@Column(name = "password", nullable = false)
	@NotBlank(message = "Password must be provided.")
	@Size(min = 5, max = 20, message = "Password must be between {min} and {max} characters long.")
	private String password;

	@JsonIgnore
	@NotBlank(message = "Confirm Password must be provided.")
	@Size(min = 5, max = 20, message = "Confirm Password must be between {min} and {max} characters long.")
    private String confirmPassword;

	@Column(nullable = false)
	@NotBlank(message = "First name must be provided.")
	@Size(min=2, max=30, message = "First name must be between {min} and {max} characters long.")
	private String firstName;
	
	@Column(nullable = false)
	@NotBlank(message = "Last name must be provided.")
	@Size(min=2, max=30, message = "First name must be between {min} and {max} characters long.")
	private String lastName;
	
	@Column(nullable = false, unique = true)
	@NotNull(message = "Email must be provided.")
	@Email(message = "Email is not valid.")
	private String email;
	
	private EUserRole role;
	
	/*@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "userEntity")
	private UserEntity userEntity;*/
	
	@JsonBackReference
	@OneToMany(mappedBy = "teacherEntity", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	protected List<Grade_OcenaEntity> gradeOcenaEntity = new ArrayList<>();
	
	@JsonBackReference
	@OneToMany(mappedBy = "teacherEntity", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	protected List<SubjectEntity> subjects = new ArrayList<>();
	
	@JsonBackReference
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "teacherSubject", 
        joinColumns = { @JoinColumn(name = "teacherId") }, 
        inverseJoinColumns = { @JoinColumn(name = "subjectId") }
    )
    Set<SubjectEntity> predmeti = new HashSet<>();
	
	@JsonBackReference
	@OneToMany(mappedBy = "teacherEntity", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	protected List<ReportEntity> reportEntity = new ArrayList<>();
	
	

	public List<Grade_OcenaEntity> getGradeOcenaEntity() {
		return gradeOcenaEntity;
	}

	public void setGradeOcenaEntity(List<Grade_OcenaEntity> gradeOcenaEntity) {
		this.gradeOcenaEntity = gradeOcenaEntity;
	}

	public List<SubjectEntity> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectEntity> subjects) {
		this.subjects = subjects;
	}

	public Set<SubjectEntity> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(Set<SubjectEntity> predmeti) {
		this.predmeti = predmeti;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public EUserRole getRole() {
		return role;
	}

	public void setRole(EUserRole role) {
		this.role = role;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public TeacherEntity () {
		super();
	}


	

	
	
	
	

}
