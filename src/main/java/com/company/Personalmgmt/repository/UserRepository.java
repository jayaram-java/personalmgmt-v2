package com.company.Personalmgmt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsernameAndPassword(String username, String password);
	
	Optional<User> findById(long id);
	
	User findByUsername(String name);

}
