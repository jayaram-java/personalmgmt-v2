package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.PersonalInfo;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> {

}
