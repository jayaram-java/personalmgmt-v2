package com.company.Personalmgmt.service;

import com.company.Personalmgmt.dto.PropertyDto;

public interface DevelopingService {

	boolean createHeadersWithAuthentication();

	boolean createIssueAtJira();

	boolean savePropertyDetails(PropertyDto propertyDto);

	boolean checkingJPARepository();
	
	boolean completableFutureJava8();

}
