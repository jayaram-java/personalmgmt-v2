package com.company.Personalmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.WorkingCompanyDetail;

@Repository
public interface WorkingCompanyDetailRepository extends JpaRepository<WorkingCompanyDetail, Long> {

	List<WorkingCompanyDetail> findByPersonalInfoId(Long id);

}
