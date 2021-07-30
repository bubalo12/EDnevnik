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

import com.iktpreobuka.EDnevnik.entities.AdminEntity;
import com.iktpreobuka.EDnevnik.entities.ParentEntity;
import com.iktpreobuka.EDnevnik.entities.dto.AdminRegisterDTO;
import com.iktpreobuka.EDnevnik.entities.dto.ParentRegisterDTO;
import com.iktpreobuka.EDnevnik.repositories.ParentRepository;
import com.iktpreobuka.EDnevnik.utils.ParentCustomValidator;

@RequestMapping ("users/parents/")
@RestController
public class ParentController {
	
	@Autowired
	ParentCustomValidator parentValidator;
	
	@Autowired
	ParentRepository parentRepository;
	
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.addValidators(parentValidator);
	}

	@PostMapping("/")
	public ResponseEntity<?> createNewParent(@Valid @RequestBody ParentRegisterDTO parentDTO, BindingResult result) {
		
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
			} else {
			parentValidator.validate(parentDTO, result);
			}


		ParentEntity newParent = new ParentEntity();
		newParent.setFirstName(parentDTO.getFirstName());
		newParent.setLastName(parentDTO.getLastName());
		newParent.setUsername(parentDTO.getUsername());
		newParent.setPassword(parentDTO.getPassword());
		newParent.setConfirmPassword(parentDTO.getConfirmPassword());
		newParent.setEmail(parentDTO.getEmail());
		newParent.setRole(parentDTO.getRole());

		parentRepository.save(newParent);

		return new ResponseEntity<ParentEntity>(newParent, HttpStatus.CREATED);

	}

	@GetMapping("/")
	public ResponseEntity<?> getParents() {
		return new ResponseEntity<List<ParentEntity>>((List<ParentEntity>) parentRepository.findAll(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteParent(@PathVariable Integer id) {
		if (parentRepository.existsById(id)) {
			ParentEntity parent = parentRepository.findById(id).get();
			parentRepository.delete(parent);
			return new ResponseEntity<ParentEntity>(parent, HttpStatus.OK);
		}
		return null;
	}

	@PutMapping("/change/{id}")
	public ResponseEntity<?> changeParent(@RequestBody ParentRegisterDTO changedParent, @PathVariable Integer id) {
		if (parentRepository.existsById(id)) {

			ParentEntity parent = parentRepository.findById(id).get();
			if (changedParent.getFirstName() != null)
				parent.setFirstName(changedParent.getFirstName());
			if (changedParent.getLastName() != null)
				parent.setLastName(changedParent.getLastName());
			if (changedParent.getUsername() != null)
				parent.setUsername(changedParent.getUsername());
			if (changedParent.getEmail() != null)
				parent.setEmail(changedParent.getEmail());
			if (changedParent.getPassword() != null)
				parent.setPassword(changedParent.getPassword());
			parentRepository.save(parent);

			return new ResponseEntity<ParentRegisterDTO>(changedParent, HttpStatus.FOUND);
		}
		return null;
	}
	
	
	
	
	
	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
		}
}
