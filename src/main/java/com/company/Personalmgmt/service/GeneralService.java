package com.company.Personalmgmt.service;

import java.util.List;
import java.util.Map;

import com.company.Personalmgmt.dto.GeneralDto;
import com.company.Personalmgmt.dto.MoveForwardDto;

public interface GeneralService {

	List<GeneralDto> getBalanceSheetDetails(String month, String year);

	List<MoveForwardDto> getMoveForwardDetails();

	Map<String,Object> timeDifference(String startDate, String endDate);
	
	GeneralDto getTimeFromZone(String timezone);
	
	List<GeneralDto> getBalanceSheetDetailsThisYear();
	
	List<GeneralDto> getBalanceSheetDetailsThisYearFilter(String year);
	
	String enableExpenseDetail();

}
