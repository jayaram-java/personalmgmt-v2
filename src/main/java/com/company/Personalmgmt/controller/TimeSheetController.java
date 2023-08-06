package com.company.Personalmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.Personalmgmt.dto.KeyNotesDto;
import com.company.Personalmgmt.model.TimeSheetDetails;
import com.company.Personalmgmt.service.TimeSheetService;

@RestController
@RequestMapping("/timesheet")
public class TimeSheetController {

	@Autowired
	TimeSheetService timeSheetService;

	@GetMapping(value = "/getAllDetails")
	public List<TimeSheetDetails> getAllKeyNotesDetails() {

		List<TimeSheetDetails> timeSheetDetails = timeSheetService.getAllTimeSheetDetails();

		return timeSheetDetails;
	}

	@PutMapping(value = "/updateDetails")
	public boolean updateTimeSheet(@RequestBody TimeSheetDetails timeSheetDetails) {

		boolean response = timeSheetService.updateTimeSheetDetails(timeSheetDetails);

		return response;
	}
	
	@GetMapping(value = "/getDetailsFromId")
	public TimeSheetDetails getTimeSheetDetailsFromId(@RequestParam Long id) {
		

		TimeSheetDetails timeSheetDetails = timeSheetService.getTimeSheetDetailsFromId(id);

		return timeSheetDetails;
	}

}
