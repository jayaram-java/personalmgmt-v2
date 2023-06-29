package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.StartupDetail;

@Repository
public interface StartupDetailRepository extends JpaRepository<StartupDetail,Long> {

}
