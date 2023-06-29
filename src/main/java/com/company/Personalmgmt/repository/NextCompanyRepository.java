package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.NextCompany;

@Repository
public interface NextCompanyRepository extends JpaRepository<NextCompany, Long> {

}
