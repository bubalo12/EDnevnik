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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.EDnevnik.entities.Grade_OcenaEntity;
import com.iktpreobuka.EDnevnik.entities.Grade_RazredEntity;
import com.iktpreobuka.EDnevnik.entities.StudentEntity;
import com.iktpreobuka.EDnevnik.entities.dto.Grade_OcenaRegisterDTO;
import com.iktpreobuka.EDnevnik.entities.dto.Grade_RazredRegisterDTO;
import com.iktpreobuka.EDnevnik.repositories.Grade_RazredRepository;
import com.iktpreobuka.EDnevnik.repositories.StudentRepository;

@RequestMapping("/razred")
@RestController
public class Grade_RazredController {

	@Autowired
	Grade_RazredRepository razredRepository;

	@Autowired
	StudentRepository studentRepository;

	@PostMapping("/")
	public ResponseEntity<?> createNewRazred(@RequestBody Grade_RazredRegisterDTO razredDTO) {

		Grade_RazredEntity newRazred = new Grade_RazredEntity();

		newRazred.setClassNo(razredDTO.getClassNo());
		newRazred.setDepartmentNo(razredDTO.getDepartmentNo());

		razredRepository.save(newRazred);

		return new ResponseEntity<Grade_RazredEntity>(newRazred, HttpStatus.CREATED);

	}

	@GetMapping("/")
	public ResponseEntity<?> getRazred() {
		return new ResponseEntity<List<Grade_RazredEntity>>((List<Grade_RazredEntity>) razredRepository.findAll(),
				HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRazred(@PathVariable Integer id) {
		if (razredRepository.existsById(id)) {
			Grade_RazredEntity razred = razredRepository.findById(id).get();
			razredRepository.delete(razred);
			return new ResponseEntity<Grade_RazredEntity>(razred, HttpStatus.OK);
		}
		return null;
	}

	@PutMapping("/change/{id}")
	public ResponseEntity<?> changeRazred(@RequestBody Grade_RazredRegisterDTO changedRazred,
			@PathVariable Integer id) {
		if (razredRepository.existsById(id)) {

			Grade_RazredEntity razred = razredRepository.findById(id).get();
			if (changedRazred.getClassNo() != null)
				razred.setClassNo(changedRazred.getClassNo());
			if (changedRazred.getDepartmentNo() != null)
				razred.setDepartmentNo(changedRazred.getDepartmentNo());
			razredRepository.save(razred);

			return new ResponseEntity<Grade_RazredRegisterDTO>(changedRazred, HttpStatus.FOUND);
		}
		return null;
	}

	@PutMapping(path = "/{id}/{studentID}")
	public ResponseEntity<?> addStudentToClass(@PathVariable Integer id, @PathVariable Integer studentID) {

		if (razredRepository.existsById(id)) {
			if (studentRepository.existsById(studentID))

			{

				Grade_RazredEntity razred = razredRepository.findById(id).get();
				StudentEntity student = studentRepository.findById(studentID).get();

				student.setGradeRazredEntity(razred);
				studentRepository.save(student);

				return new ResponseEntity<StudentEntity>(student, HttpStatus.OK);

			}
		}
		return null;

	}

}
