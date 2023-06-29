package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.AdminLogin;

@Repository
public interface AdminLoginRepository extends JpaRepository<AdminLogin, Long> {

	AdminLogin findByUsernameAndPassword(String username, String password);

}
