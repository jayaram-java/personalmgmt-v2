package com.company.Personalmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.FileMgmtDetails;

@Repository
public interface FileMgmtRepository extends JpaRepository<FileMgmtDetails, Long> {

	
	
	
	List<FileMgmtDetails> findByUserId(long id);

}
