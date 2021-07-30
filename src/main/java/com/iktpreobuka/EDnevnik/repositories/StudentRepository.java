package com.iktpreobuka.EDnevnik.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


import com.iktpreobuka.EDnevnik.entities.StudentEntity;


public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {

	
	
	public StudentEntity findById(int id);

	public StudentEntity findByUsername(String username);

	

}
