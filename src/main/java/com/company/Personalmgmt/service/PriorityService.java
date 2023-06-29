package com.company.Personalmgmt.service;

import java.util.List;

import com.company.Personalmgmt.dto.PriorityDto;

public interface PriorityService {

	boolean savePriorityDetails(PriorityDto priorityDto);

	List<PriorityDto> getAllPriorityList();

	PriorityDto getpriorityById(Long id);

}
