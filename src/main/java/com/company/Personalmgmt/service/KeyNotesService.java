package com.company.Personalmgmt.service;

import java.util.List;

import com.company.Personalmgmt.dto.KeyNotesDto;

public interface KeyNotesService {

	boolean saveKeyNotesDetails(KeyNotesDto keyNotesDto); // create | update
	
	List<KeyNotesDto> getAllKeyNotesDetails(); // read
	
	KeyNotesDto getKeyNotesDetailsFromId(Long id); // fetch
	
	List<KeyNotesDto> getRelevantKeyNotesDetails(String name);
	
	List<KeyNotesDto>  getAllKeyNotesCategory();
	
}
