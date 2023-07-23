package com.company.Personalmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.EmployeeTaskDetail;

@Repository
public interface EmployeeTaskDetailRepository extends JpaRepository<EmployeeTaskDetail, Long> {

	EmployeeTaskDetail findById(long id);

	List<EmployeeTaskDetail> findByOrderByIdDesc();
	
	List<EmployeeTaskDetail> findByOfficeOrderByIdDesc(String office);

}
