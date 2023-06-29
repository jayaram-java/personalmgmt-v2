package com.company.Personalmgmt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.Personalmgmt.dto.GeneralDto;
import com.company.Personalmgmt.dto.MoveForwardDto;
import com.company.Personalmgmt.service.GeneralService;

@Controller
public class GeneralController {

	@Autowired
	GeneralService generalService;

	@RequestMapping(value = "/getbalancesheetdetails",method= {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<GeneralDto> getBalanceSheetDetails(@RequestParam String month, @RequestParam String year) {

		List<GeneralDto> generalDtos = generalService.getBalanceSheetDetails(month, year);

		return generalDtos;

	}
	
	@RequestMapping(value = "/getbalancesheetdetailsthisyear",method= {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<GeneralDto> getBalanceSheetDetailsThisYear() {

		List<GeneralDto> generalDtos = generalService.getBalanceSheetDetailsThisYear();

		return generalDtos;

	}
	
	@RequestMapping(value = "/getbalancesheetdetailsthisyearFilter",method= {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<GeneralDto> getBalanceSheetDetailsThisYearFilter(@RequestParam String year) {

		List<GeneralDto> generalDtos = generalService.getBalanceSheetDetailsThisYearFilter(year);

		return generalDtos;

	}

	@RequestMapping(value = "/getMoveForwardDetails",method= {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<MoveForwardDto> getMoveForwardDetails() {

		List<MoveForwardDto> moveForwarddtos = generalService.getMoveForwardDetails();

		return moveForwarddtos;
	}
	
	@RequestMapping(value = "/gettimedifference",method= {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Map<String,Object> getTimeDifference(@RequestParam String startDate, @RequestParam String endDate) {

		Map<String,Object> result = generalService.timeDifference(startDate, endDate);

		return result;
	}
	
	@RequestMapping(value = "/gettimefromzone",method= {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public GeneralDto getTimeFromZone(@RequestParam String timezone) {

		GeneralDto generalDto = generalService.getTimeFromZone(timezone);

		return generalDto;
	}
	
	@RequestMapping(value = "/registerExpEmailSchedule",method= {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String enableExpenseDetail() {

		String result = generalService.enableExpenseDetail();

		return result;
	}
}
