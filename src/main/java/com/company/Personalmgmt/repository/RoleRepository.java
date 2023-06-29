package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.Personalmgmt.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRolename(String roleName);
}
