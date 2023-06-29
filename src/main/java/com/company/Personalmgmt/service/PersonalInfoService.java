package com.company.Personalmgmt.service;

import java.util.List;

import com.company.Personalmgmt.dto.PersonalInfoDto;
import com.company.Personalmgmt.model.WorkingCompanyDetail;

public interface PersonalInfoService {

	public boolean savePersonalInfo(PersonalInfoDto personalInfoDto);

	public List<PersonalInfoDto> fetchAllPersonalInfo();

	public boolean saveWorkingCompanyDetail(WorkingCompanyDetail workingCompanyDetail);

	List<WorkingCompanyDetail> fetchAllWorkingCompanyDetail();
	
	public List<PersonalInfoDto> fetchPersonalInfo();

}
