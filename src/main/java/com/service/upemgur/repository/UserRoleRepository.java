package com.service.upemgur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.service.upemgur.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> , JpaSpecificationExecutor<UserRole>  {



}
