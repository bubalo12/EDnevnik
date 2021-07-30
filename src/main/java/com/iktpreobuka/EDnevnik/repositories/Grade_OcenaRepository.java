package com.iktpreobuka.EDnevnik.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.EDnevnik.entities.Grade_OcenaEntity;
import com.iktpreobuka.EDnevnik.entities.StudentEntity;

public interface Grade_OcenaRepository extends CrudRepository<Grade_OcenaEntity, Integer> {

	

	public Grade_OcenaEntity findById(int id);
	
	

}
