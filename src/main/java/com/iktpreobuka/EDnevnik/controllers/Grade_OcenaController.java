package com.iktpreobuka.EDnevnik.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.EDnevnik.entities.AdminEntity;
import com.iktpreobuka.EDnevnik.entities.Grade_OcenaEntity;
import com.iktpreobuka.EDnevnik.entities.ParentEntity;
import com.iktpreobuka.EDnevnik.entities.StudentEntity;
import com.iktpreobuka.EDnevnik.entities.SubjectEntity;
import com.iktpreobuka.EDnevnik.entities.TeacherEntity;
import com.iktpreobuka.EDnevnik.entities.dto.Grade_OcenaRegisterDTO;
import com.iktpreobuka.EDnevnik.entities.dto.SubjectRegisterDTO;
import com.iktpreobuka.EDnevnik.repositories.Grade_OcenaRepository;
import com.iktpreobuka.EDnevnik.repositories.StudentRepository;
import com.iktpreobuka.EDnevnik.repositories.SubjectRepository;
import com.iktpreobuka.EDnevnik.repositories.TeacherRepository;

@RequestMapping("/ocene")
@RestController
public class Grade_OcenaController {
	
	@Autowired
	Grade_OcenaRepository ocenaRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	TeacherRepository teacherRepository;
	
	@PostMapping("/")
	public ResponseEntity<?> createNewOcena(@DateTimeFormat(iso = ISO.DATE) @RequestBody Grade_OcenaRegisterDTO ocenaDTO) {
		
		Grade_OcenaEntity newOcena = new Grade_OcenaEntity();
		
		newOcena.setGrade(ocenaDTO.getGrade());
		newOcena.setDate(ocenaDTO.getDate());
		
		ocenaRepository.save(newOcena);
		
		return new ResponseEntity<Grade_OcenaEntity>(newOcena, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getOcene() {
		return new ResponseEntity<List<Grade_OcenaEntity>>((List<Grade_OcenaEntity>) ocenaRepository.findAll(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOcena(@PathVariable Integer id) {
		if (ocenaRepository.existsById(id)) {
			Grade_OcenaEntity ocena = ocenaRepository.findById(id).get();
			ocenaRepository.delete(ocena);
			return new ResponseEntity<Grade_OcenaEntity>(ocena, HttpStatus.OK);
		}
		return null;
	}

	@PutMapping("/change/{id}")
	public ResponseEntity<?> changeOcena(@DateTimeFormat(iso = ISO.DATE) @RequestBody Grade_OcenaRegisterDTO changedOcena, @PathVariable Integer id) {
		if (ocenaRepository.existsById(id)) {

			Grade_OcenaEntity ocena = ocenaRepository.findById(id).get();
			if (changedOcena.getGrade() != null)
				ocena.setGrade(changedOcena.getGrade());
			if (changedOcena.getDate() != null)
				ocena.setDate(changedOcena.getDate());
			ocenaRepository.save(ocena);

			return new ResponseEntity<Grade_OcenaRegisterDTO>(changedOcena, HttpStatus.FOUND);
		}
		return null;
	}
	
	@PutMapping("/{gradeID}/{studentID}/{subjectID}")
	public ResponseEntity<?> addGradeToStudentInSubject(@PathVariable Integer gradeID, @PathVariable Integer studentID, @PathVariable Integer subjectID) {
		
		Grade_OcenaEntity ocena = ocenaRepository.findById(gradeID).get();
		StudentEntity student = studentRepository.findById(studentID).get();
		SubjectEntity subject = subjectRepository.findById(subjectID).get();
		
		ocena.setSubjectEntity(subject);
		ocena.setStudentEntity(student);
		ocenaRepository.save(ocena);
		
		
		

		return new ResponseEntity<Grade_OcenaEntity>(ocena, HttpStatus.OK);
	}
	
	@PutMapping("/{gradeID}/{studentID}")
	public ResponseEntity<?> addGradeToStudent(@PathVariable Integer gradeID, @PathVariable Integer studentID) {
		
		Grade_OcenaEntity ocena = ocenaRepository.findById(gradeID).get();
		StudentEntity student = studentRepository.findById(studentID).get();
		
		ocena.setStudentEntity(student);
		ocenaRepository.save(ocena);

		return new ResponseEntity<Grade_OcenaEntity>(ocena, HttpStatus.OK);
	}
	
	@PutMapping("/teacher/{gradeID}/{teacherID}")
	public ResponseEntity<?> addGradeToTeacher(@PathVariable Integer gradeID, @PathVariable Integer teacherID) {
		
		Grade_OcenaEntity ocena = ocenaRepository.findById(gradeID).get();
		TeacherEntity teacher = teacherRepository.findById(teacherID).get();
		
		ocena.setTeacherEntity(teacher);
		ocenaRepository.save(ocena);

		return new ResponseEntity<Grade_OcenaEntity>(ocena, HttpStatus.OK);
	}
	
	
	
	
	@GetMapping(path = "/{id}")
	public Grade_OcenaEntity findGradeById(@PathVariable Integer id) {
		if (ocenaRepository.existsById(id)) {
			return ocenaRepository.findById(id).get();
		}
		return null;
	}
	
/*
	@GetMapping(path = "/findByStudentId/{StudentId}")
	public Grade_OcenaEntity findGradeByStudentId(@PathVariable Integer StudentId) {
		if(studentRepository.existsById(StudentId)) {
			Grade_OcenaEntity ocena= ocenaRepository.findAllByStudentEntityStudentId(StudentId);
			return ocena;
		}
		
		
		return null;
	}
	*/
	
	@PostMapping("/student/{studentID}/{subjectID}")
	public ResponseEntity<?> AddGradeToStudentandSubject(@DateTimeFormat(iso = ISO.DATE) @RequestBody Grade_OcenaRegisterDTO ocenaDTO, @PathVariable Integer studentID, 
			@PathVariable Integer subjectID) {
		if (studentRepository.existsById(studentID) && subjectRepository.existsById(subjectID)) {

			Grade_OcenaEntity ocena = new Grade_OcenaEntity();
			
			ocena.setGrade(ocenaDTO.getGrade());
			ocena.setDate(ocenaDTO.getDate());						//Ako nista drugo, barem proveravamo da li taj ucenik pohadja taj predmet
			
			ocenaRepository.save(ocena);

			return new ResponseEntity<Grade_OcenaEntity>(ocena, HttpStatus.OK);
		}
		return null;
	}
	

}
