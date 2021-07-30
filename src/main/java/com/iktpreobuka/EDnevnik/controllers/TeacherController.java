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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.EDnevnik.entities.Grade_RazredEntity;
import com.iktpreobuka.EDnevnik.entities.StudentEntity;
import com.iktpreobuka.EDnevnik.entities.SubjectEntity;
import com.iktpreobuka.EDnevnik.entities.TeacherEntity;

import com.iktpreobuka.EDnevnik.entities.dto.TeacherRegisterDTO;
import com.iktpreobuka.EDnevnik.repositories.SubjectRepository;
import com.iktpreobuka.EDnevnik.repositories.TeacherRepository;
import com.iktpreobuka.EDnevnik.utils.TeacherCustomValidator;

@RequestMapping("/users/teachers")
@RestController
public class TeacherController {

	@Autowired
	TeacherRepository teacherRepository;

	@Autowired
	TeacherCustomValidator teacherValidator;

	@Autowired
	SubjectRepository subjectRepository;

	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.addValidators(teacherValidator);
	}

	@PostMapping("/")
	public ResponseEntity<?> createNewTeacher(@Valid @RequestBody TeacherRegisterDTO teacherDTO, BindingResult result) {

		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		} else {
			teacherValidator.validate(teacherDTO, result);
		}

		TeacherEntity newTeacher = new TeacherEntity();
		newTeacher.setFirstName(teacherDTO.getFirstName());
		newTeacher.setLastName(teacherDTO.getLastName());
		newTeacher.setUsername(teacherDTO.getUsername());
		newTeacher.setPassword(teacherDTO.getPassword());
		newTeacher.setConfirmPassword(teacherDTO.getConfirmPassword());
		newTeacher.setEmail(teacherDTO.getEmail());
		newTeacher.setRole(teacherDTO.getRole());

		teacherRepository.save(newTeacher);

		return new ResponseEntity<TeacherEntity>(newTeacher, HttpStatus.CREATED);

	}

	@GetMapping("/")
	public ResponseEntity<?> getTeacher() {
		return new ResponseEntity<List<TeacherEntity>>((List<TeacherEntity>) teacherRepository.findAll(),
				HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTeacher(@PathVariable Integer id) {
		if (teacherRepository.existsById(id)) {
			TeacherEntity teacher = teacherRepository.findById(id).get();
			teacherRepository.delete(teacher);
			return new ResponseEntity<TeacherEntity>(teacher, HttpStatus.OK);
		}
		return null;
	}

	@PutMapping("/change/{id}")
	public ResponseEntity<?> changeTeacher(@RequestBody TeacherRegisterDTO changedTeacher, @PathVariable Integer id) {
		if (teacherRepository.existsById(id)) {

			TeacherEntity teacher = teacherRepository.findById(id).get();
			if (changedTeacher.getFirstName() != null)
				teacher.setFirstName(changedTeacher.getFirstName());
			if (changedTeacher.getLastName() != null)
				teacher.setLastName(changedTeacher.getLastName());
			if (changedTeacher.getUsername() != null)
				teacher.setUsername(changedTeacher.getUsername());
			if (changedTeacher.getEmail() != null)
				teacher.setEmail(changedTeacher.getEmail());
			if (changedTeacher.getPassword() != null)
				teacher.setPassword(changedTeacher.getPassword());
			teacherRepository.save(teacher);

			return new ResponseEntity<TeacherRegisterDTO>(changedTeacher, HttpStatus.FOUND);
		}
		return null;
	}

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
	}

	
}
