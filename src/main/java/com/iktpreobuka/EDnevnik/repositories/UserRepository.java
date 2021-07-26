package com.iktpreobuka.EDnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.EDnevnik.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

}
