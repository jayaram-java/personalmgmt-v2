package com.company.Personalmgmt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.StatusMaster;

@Repository
public interface StatusMasterRepository extends JpaRepository<StatusMaster, Long> {
	
	Optional<StatusMaster> findByName(String name);
	
	
}
