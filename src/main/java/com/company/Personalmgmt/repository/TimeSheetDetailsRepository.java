package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.TimeSheetDetails;

@Repository
public interface TimeSheetDetailsRepository extends JpaRepository<TimeSheetDetails, Long>  {

}
