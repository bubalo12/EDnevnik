package com.iktpreobuka.EDnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.EDnevnik.entities.TeacherEntity;

public interface TeacherRepository extends CrudRepository<TeacherEntity, Integer> {

}
