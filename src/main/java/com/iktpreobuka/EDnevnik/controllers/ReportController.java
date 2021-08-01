package com.iktpreobuka.EDnevnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.EDnevnik.entities.Grade_OcenaEntity;
import com.iktpreobuka.EDnevnik.entities.Grade_RazredEntity;
import com.iktpreobuka.EDnevnik.entities.ReportEntity;
import com.iktpreobuka.EDnevnik.entities.StudentEntity;
import com.iktpreobuka.EDnevnik.entities.SubjectEntity;
import com.iktpreobuka.EDnevnik.entities.TeacherEntity;
import com.iktpreobuka.EDnevnik.repositories.Grade_OcenaRepository;
import com.iktpreobuka.EDnevnik.repositories.Grade_RazredRepository;
import com.iktpreobuka.EDnevnik.repositories.ReportRepository;
import com.iktpreobuka.EDnevnik.repositories.StudentRepository;
import com.iktpreobuka.EDnevnik.repositories.SubjectRepository;
import com.iktpreobuka.EDnevnik.repositories.TeacherRepository;

@RequestMapping("/report")
@RestController
public class ReportController {
	
	@Autowired
	ReportRepository reportRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	Grade_RazredRepository razredRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	Grade_OcenaRepository ocenaRepository;
	
	@PutMapping ("/addEverythingToReport")
	public ResponseEntity<?> addEverythingToReport (@RequestParam Integer classID, @RequestParam Integer subjectID, 
			@RequestParam Integer studentID, @RequestParam Integer ocenaID, @RequestParam Integer teacherID) {
		
		ReportEntity report = new ReportEntity();
		
		Grade_RazredEntity razred = razredRepository.findById(classID).get();
		SubjectEntity subject = subjectRepository.findById(subjectID).get();
		StudentEntity student = studentRepository.findById(studentID).get();
		Grade_OcenaEntity ocena = ocenaRepository.findById(ocenaID).get();
		TeacherEntity teacher = teacherRepository.findById(teacherID).get();
		
		report.setRazredEntity(razred);
		report.setSubjectEntity(subject);
		report.setStudentEntity(student);
		report.setOcenaEntity(ocena);
		report.setTeacherEntity(teacher);
		
		
		return new ResponseEntity<>(report,HttpStatus.OK);
		
	}
	

}
