package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.PropertyCategory;

@Repository
public interface PropertyCategoryRepository extends JpaRepository<PropertyCategory, Long> {

	PropertyCategory findByName(String name);

}
