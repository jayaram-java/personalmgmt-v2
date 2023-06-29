package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.ClientCategoryMaster;

@Repository
public interface ClientCategoryMasterRepository extends JpaRepository<ClientCategoryMaster, Long> {

	ClientCategoryMaster findByName(String name);

}
