package com.company.Personalmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.KeyNotesCategory;

@Repository
public interface KeyNotesCategoryRepository extends JpaRepository<KeyNotesCategory, Long> {

	KeyNotesCategory findByName(String name);

	List<KeyNotesCategory> findByUserId(long userid);
	
	KeyNotesCategory findByStatusAndUserIdAndPrimaryFlag(String status, long userid, String primaryFlag);
	
	List<KeyNotesCategory> findByStatusAndUserId(String status, long userid);

}
