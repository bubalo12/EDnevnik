package com.iktpreobuka.EDnevnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.EDnevnik.entities.AdminEntity;
import com.iktpreobuka.EDnevnik.entities.dto.AdminRegisterDTO;
import com.iktpreobuka.EDnevnik.repositories.AdminRepository;


@RequestMapping ("/users/admins")
@RestController
public class AdminController {
	
	@Autowired
	AdminRepository adminRepository;

	
	@PostMapping("/")
	public ResponseEntity<?> createNewAdmin(@RequestBody AdminRegisterDTO adminDTO) {
		
		AdminEntity newAdmin = new AdminEntity();
		newAdmin.setFirstName(adminDTO.getFirstName());
		newAdmin.setLastName(adminDTO.getLastName());
		newAdmin.setUsername(adminDTO.getUsername());
		newAdmin.setPassword(adminDTO.getPassword());
		newAdmin.setEmail(adminDTO.getEmail());
		newAdmin.setRole(adminDTO.getRole());
		
		adminRepository.save(newAdmin);
		
		return new ResponseEntity<AdminEntity>(newAdmin, HttpStatus.CREATED);
		
		
	}
	
	
}
