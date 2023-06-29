package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.YearMaster;

@Repository
public interface YearMasterRepository extends JpaRepository<YearMaster, Long> {

}
