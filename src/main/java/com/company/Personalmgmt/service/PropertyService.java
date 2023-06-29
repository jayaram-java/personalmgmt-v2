package com.company.Personalmgmt.service;

import java.util.List;

import com.company.Personalmgmt.dto.PropertyDto;

public interface PropertyService {

	public List<PropertyDto> fetchAllPropertyDetails();
	
	boolean savePropertyDetails(PropertyDto propertyDto);
	
	PropertyDto getPropertyDetailsFromId(Long id);
	
	

}
