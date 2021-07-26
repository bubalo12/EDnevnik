package com.iktpreobuka.EDnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.EDnevnik.entities.StudentEntity;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {

}
