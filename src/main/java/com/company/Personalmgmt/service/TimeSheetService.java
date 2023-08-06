package com.company.Personalmgmt.service;

import java.util.List;

import com.company.Personalmgmt.model.TimeSheetDetails;

public interface TimeSheetService {

	List<TimeSheetDetails> getAllTimeSheetDetails();

	boolean updateTimeSheetDetails(TimeSheetDetails timeSheetDetails);

	TimeSheetDetails getTimeSheetDetailsFromId(Long id);
}
