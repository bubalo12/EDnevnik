package com.iktpreobuka.EDnevnik.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.EDnevnik.entities.AdminEntity;
import com.iktpreobuka.EDnevnik.entities.Grade_RazredEntity;
import com.iktpreobuka.EDnevnik.entities.ParentEntity;
import com.iktpreobuka.EDnevnik.entities.StudentEntity;
import com.iktpreobuka.EDnevnik.entities.dto.AdminRegisterDTO;
import com.iktpreobuka.EDnevnik.entities.dto.StudentRegisterDTO;
import com.iktpreobuka.EDnevnik.repositories.Grade_RazredRepository;
import com.iktpreobuka.EDnevnik.repositories.ParentRepository;
import com.iktpreobuka.EDnevnik.repositories.StudentRepository;
import com.iktpreobuka.EDnevnik.utils.StudentCustomValidator;

@RequestMapping("/users/students")
@RestController
public class StudentController {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	StudentCustomValidator studentValidator;

	@Autowired
	Grade_RazredRepository razredRepository;

	@Autowired
	ParentRepository parentRepository;

	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.addValidators(studentValidator);
	}

	@PostMapping("/")
	public ResponseEntity<?> createNewStudent(@Valid @RequestBody StudentRegisterDTO studentDTO, BindingResult result) {

		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		} else {
			studentValidator.validate(studentDTO, result);
		}

		StudentEntity newStudent = new StudentEntity();
		newStudent.setFirstName(studentDTO.getFirstName());
		newStudent.setLastName(studentDTO.getLastName());
		newStudent.setUsername(studentDTO.getUsername());
		newStudent.setPassword(studentDTO.getPassword());
		newStudent.setConfirmPassword(studentDTO.getConfirmPassword());
		newStudent.setEmail(studentDTO.getEmail());
		newStudent.setRole(studentDTO.getRole());

		studentRepository.save(newStudent);

		return new ResponseEntity<StudentEntity>(newStudent, HttpStatus.CREATED);

	}

	@GetMapping("/")
	public ResponseEntity<?> getStudents() {
		return new ResponseEntity<List<StudentEntity>>((List<StudentEntity>) studentRepository.findAll(),
				HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
		if (studentRepository.existsById(id)) {
			StudentEntity student = studentRepository.findById(id).get();
			studentRepository.delete(student);
			return new ResponseEntity<StudentEntity>(student, HttpStatus.OK);
		}
		return null;
	}

	@PutMapping("/change/{id}")
	public ResponseEntity<?> changeStudent(@RequestBody StudentRegisterDTO changedStudent, @PathVariable Integer id) {
		if (studentRepository.existsById(id)) {

			StudentEntity student = studentRepository.findById(id).get();
			if (changedStudent.getFirstName() != null)
				student.setFirstName(changedStudent.getFirstName());
			if (changedStudent.getLastName() != null)
				student.setLastName(changedStudent.getLastName());
			if (changedStudent.getUsername() != null)
				student.setUsername(changedStudent.getUsername());
			if (changedStudent.getEmail() != null)
				student.setEmail(changedStudent.getEmail());
			if (changedStudent.getPassword() != null)
				student.setPassword(changedStudent.getPassword());
			studentRepository.save(student);

			return new ResponseEntity<StudentRegisterDTO>(changedStudent, HttpStatus.FOUND);
		}
		return null;
	}

	@PutMapping(path = "/{studentID}/{id}")
	public ResponseEntity<?> addStudentToClass(@PathVariable Integer studentID, @PathVariable Integer id) {

		Grade_RazredEntity razred = razredRepository.findById(id).get();
		StudentEntity student = studentRepository.findById(studentID).get();
		student.setGradeRazredEntity(razred);
		studentRepository.save(student);

		return new ResponseEntity<StudentEntity>(student, HttpStatus.OK);

	}

	@PutMapping("/toParent/{studentID}/{parentID}")
	public ResponseEntity<?> addStudentToParent(@PathVariable Integer studentID, @PathVariable Integer parentID) {
		
		ParentEntity parent = parentRepository.findById(parentID).get();
		StudentEntity student = studentRepository.findById(studentID).get();
		student.setParentEntity(parent);
		studentRepository.save(student);

		return new ResponseEntity<StudentEntity>(student, HttpStatus.OK);
	}

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
	}
}
