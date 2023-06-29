package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.Priority;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
	
	Priority findById(long id);

}
