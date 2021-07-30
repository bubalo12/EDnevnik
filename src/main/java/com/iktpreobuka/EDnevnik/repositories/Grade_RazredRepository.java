package com.iktpreobuka.EDnevnik.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.EDnevnik.entities.Grade_RazredEntity;
import com.iktpreobuka.EDnevnik.entities.StudentEntity;

public interface Grade_RazredRepository extends CrudRepository<Grade_RazredEntity, Integer> {

	 
	
	
	public Grade_RazredEntity findById(int id);
}
