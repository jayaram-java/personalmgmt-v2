package com.company.Personalmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.Personalmgmt.dto.CheckListCategoryDto;
import com.company.Personalmgmt.dto.CheckListDetailDto;
import com.company.Personalmgmt.service.CheckListService;

@Controller
@RequestMapping("/checklist")
public class CheckListController {

	@Autowired
	CheckListService checkListService;

	@RequestMapping(value = "/checklistcategory", method= {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<CheckListCategoryDto> getAllCheckListCategory() {

		List<CheckListCategoryDto> checkListCategoryDtos = checkListService.getAllCheckListCategory();

		return checkListCategoryDtos;

	}

	@RequestMapping(value = "/saveDetails", method= {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public boolean saveChecklist(@RequestBody CheckListDetailDto checkListDetailDto) {

		boolean response = checkListService.saveCheckListDetails(checkListDetailDto);

		return response;
	}

	@RequestMapping(value = "/getAllDetails", method= {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<CheckListDetailDto> getAllCheckListDetails() {

		List<CheckListDetailDto> checkListDetailDtos = checkListService.getAllCheckListDetails();

		return checkListDetailDtos;
	}

	@RequestMapping(value = "/getDetailsFromId", method= {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public CheckListDetailDto getCheckListDetailsFromId(@RequestParam Long id) {

		CheckListDetailDto checkListDetailDto = checkListService.getCheckListDetailsFromId(id);

		return checkListDetailDto;
	}
	
	@RequestMapping(value = "/getChecklistdetails", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<CheckListDetailDto> getRelevantChecklistDetails(@RequestParam String name) {
		
		System.out.println("Name "+ name);

		List<CheckListDetailDto> checkListDetailDtos = checkListService.getRelevantChecklistDetails(name);

		return checkListDetailDtos;
	}

}
