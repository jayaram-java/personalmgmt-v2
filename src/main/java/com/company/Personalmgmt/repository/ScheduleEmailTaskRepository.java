package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.ScheduleEmailTask;

@Repository
public interface ScheduleEmailTaskRepository extends JpaRepository<ScheduleEmailTask, Long> {

	ScheduleEmailTask findByPurposeAndMonthAndYearAndUserId(String purpose, String month, String year,long id);

}
