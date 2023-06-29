package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.PropertyDetail;

@Repository
public interface PropertyDetailRepository extends JpaRepository<PropertyDetail, Long> {

	PropertyDetail findById(long id);

}
