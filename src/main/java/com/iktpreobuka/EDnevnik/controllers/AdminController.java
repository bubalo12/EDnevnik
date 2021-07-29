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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.EDnevnik.entities.AdminEntity;
import com.iktpreobuka.EDnevnik.entities.dto.AdminRegisterDTO;
import com.iktpreobuka.EDnevnik.repositories.AdminRepository;
import com.iktpreobuka.EDnevnik.utils.AdminCustomValidator;





@RequestMapping("/users/admins")
@RestController
public class AdminController {

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	AdminCustomValidator adminValidator;

	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.addValidators(adminValidator);
	}

	@PostMapping("/")
	public ResponseEntity<?> createNewAdmin(@Valid @RequestBody AdminRegisterDTO adminDTO, BindingResult result) {
		
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
			} else {
			adminValidator.validate(adminDTO, result);
			}


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

	@GetMapping("/")
	public ResponseEntity<?> getAdmins() {
		return new ResponseEntity<List<AdminEntity>>((List<AdminEntity>) adminRepository.findAll(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAdmin(@PathVariable Integer id) {
		if (adminRepository.existsById(id)) {
			AdminEntity admin = adminRepository.findById(id).get();
			adminRepository.delete(admin);
			return new ResponseEntity<AdminEntity>(admin, HttpStatus.OK);
		}
		return null;
	}

	@PutMapping("/change/{id}")
	public ResponseEntity<?> changeAdmin(@RequestBody AdminRegisterDTO changedAdmin, @PathVariable Integer id) {
		if (adminRepository.existsById(id)) {

			AdminEntity admin = adminRepository.findById(id).get();
			if (changedAdmin.getFirstName() != null)
				admin.setFirstName(changedAdmin.getFirstName());
			if (changedAdmin.getLastName() != null)
				admin.setLastName(changedAdmin.getLastName());
			if (changedAdmin.getUsername() != null)
				admin.setUsername(changedAdmin.getUsername());
			if (changedAdmin.getEmail() != null)
				admin.setEmail(changedAdmin.getEmail());
			if (changedAdmin.getPassword() != null)
				admin.setPassword(changedAdmin.getPassword());
			adminRepository.save(admin);

			return new ResponseEntity<AdminRegisterDTO>(changedAdmin, HttpStatus.FOUND);
		}
		return null;
	}
	
	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
		}
		


}
