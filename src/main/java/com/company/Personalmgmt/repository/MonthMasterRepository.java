package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.MonthMaster;

@Repository
public interface MonthMasterRepository extends JpaRepository<MonthMaster, Long> {

}
