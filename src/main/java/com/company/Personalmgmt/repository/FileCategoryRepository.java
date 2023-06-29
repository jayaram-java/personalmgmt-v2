package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.FileCategory;

@Repository
public interface FileCategoryRepository extends JpaRepository<FileCategory, Long> {

	
	FileCategory findByName(String name);
}
