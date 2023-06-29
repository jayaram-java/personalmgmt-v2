package com.company.Personalmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.ApplicationFeature;
import com.company.Personalmgmt.model.ClientCategoryMaster;

@Repository
public interface ApplicationFeatureRepository extends JpaRepository<ApplicationFeature, Long> {

	List<ApplicationFeature> findByClientCategoryMaster(ClientCategoryMaster clientCategoryMaster);

}
