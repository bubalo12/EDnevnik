package com.iktpreobuka.EDnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.EDnevnik.entities.AdminEntity;
import com.iktpreobuka.EDnevnik.entities.dto.AdminRegisterDTO;

public interface AdminRepository extends CrudRepository<AdminEntity, Integer> {



}
