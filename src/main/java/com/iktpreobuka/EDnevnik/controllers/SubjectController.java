package com.iktpreobuka.EDnevnik.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.EDnevnik.entities.ParentEntity;
import com.iktpreobuka.EDnevnik.entities.StudentEntity;
import com.iktpreobuka.EDnevnik.entities.SubjectEntity;
import com.iktpreobuka.EDnevnik.entities.TeacherEntity;
import com.iktpreobuka.EDnevnik.entities.dto.StudentRegisterDTO;
import com.iktpreobuka.EDnevnik.entities.dto.SubjectRegisterDTO;
import com.iktpreobuka.EDnevnik.repositories.SubjectRepository;
import com.iktpreobuka.EDnevnik.repositories.TeacherRepository;

@RequestMapping ("/subjects")
@RestController
public class SubjectController {
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	TeacherRepository teacherRepository;
	
	@PostMapping("/")
	public ResponseEntity<?> createNewSubject( @RequestBody SubjectRegisterDTO subjectDTO) {
		
		SubjectEntity newSubject = new SubjectEntity();
		
		newSubject.setName(subjectDTO.getName());
		
		subjectRepository.save(newSubject);
		
		return new ResponseEntity<SubjectEntity>(newSubject, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getSubjects() {
		return new ResponseEntity<List<SubjectEntity>>((List<SubjectEntity>) subjectRepository.findAll(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSubject(@PathVariable Integer id) {
		if (subjectRepository.existsById(id)) {
			SubjectEntity subject = subjectRepository.findById(id).get();
			subjectRepository.delete(subject);
			return new ResponseEntity<SubjectEntity>(subject, HttpStatus.OK);
		}
		return null;
	}

	@PutMapping("/change/{id}")
	public ResponseEntity<?> changeSubject(@RequestBody SubjectRegisterDTO changedSubject, @PathVariable Integer id) {
		if (subjectRepository.existsById(id)) {

			SubjectEntity subject = subjectRepository.findById(id).get();
			if (changedSubject.getName() != null)
				subject.setName(changedSubject.getName());
			subjectRepository.save(subject);

			return new ResponseEntity<SubjectRegisterDTO>(changedSubject, HttpStatus.FOUND);
		}
		return null;
	}
	
	@PutMapping("/toTeacher/{subjectID}/{teacherID}")
	public ResponseEntity<?> addSubjectToTeacher(@PathVariable Integer subjectID, @PathVariable Integer teacherID) {
		
		SubjectEntity subject = subjectRepository.findById(subjectID).get();
		TeacherEntity teacher = teacherRepository.findById(teacherID).get();
		subject.setTeacherEntity(teacher);
		subjectRepository.save(subject);

		return new ResponseEntity<SubjectEntity>(subject, HttpStatus.OK);
	}
	
	
}
