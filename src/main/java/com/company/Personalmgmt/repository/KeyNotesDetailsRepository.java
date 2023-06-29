package com.company.Personalmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.KeyNotesDetails;

@Repository
public interface KeyNotesDetailsRepository extends JpaRepository<KeyNotesDetails, Long> {

	KeyNotesDetails findById(long id);
	
	List<KeyNotesDetails> findByKeyNotesCategoryIdAndUserId(Long id, long userid);
	
	List<KeyNotesDetails> findByKeyNotesCategoryNameAndUserId(String name, long userid);
	
}
