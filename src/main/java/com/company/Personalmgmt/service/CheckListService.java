package com.company.Personalmgmt.service;

import java.util.List;

import com.company.Personalmgmt.dto.CheckListCategoryDto;
import com.company.Personalmgmt.dto.CheckListDetailDto;

public interface CheckListService {

	List<CheckListCategoryDto> getAllCheckListCategory();
	
	boolean saveCheckListDetails(CheckListDetailDto checkListDetailDto);
	
	CheckListDetailDto getCheckListDetailsFromId(Long id);
	
	List<CheckListDetailDto> getAllCheckListDetails();
	
	List<CheckListDetailDto> getRelevantChecklistDetails(String name);
	

}
