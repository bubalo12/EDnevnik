package com.iktpreobuka.EDnevnik.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.EDnevnik.entities.StudentEntity;

@RestController
@RequestMapping ("/users")
public class UserController {
	
	//@PostMapping
	//public ResponseEntity<?> addNewUser(@RequestBody String username, @RequestBody String password) {
		
	//	return new ResponseEntity<StudentEntity>(student, HttpStatus.OK);
	//}
			
}
