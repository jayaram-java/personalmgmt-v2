package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.InterLinkSoftware;

@Repository
public interface InterLinkSoftwareRepository extends JpaRepository<InterLinkSoftware, Long>{

}
