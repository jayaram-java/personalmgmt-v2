package com.company.Personalmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.Personalmgmt.dto.PriorityDto;
import com.company.Personalmgmt.service.PriorityService;

@Controller
public class PriorityController {

	@Autowired
	PriorityService priorityService;

	@RequestMapping(value = "/saveprioritydetails")
	@ResponseBody
	public boolean savePriorityDetails(@RequestBody PriorityDto priorityDto) {

		boolean response = priorityService.savePriorityDetails(priorityDto);

		return response;
	}

	@RequestMapping(value = "/getallprioritydetails")
	@ResponseBody
	public List<PriorityDto> getAllPriorityList() {

		List<PriorityDto> priorityDtos = priorityService.getAllPriorityList();

		return priorityDtos;
	}

	@RequestMapping(value = "/getprioritydetailsbyid")
	@ResponseBody
	public PriorityDto getpriorityById(@RequestParam Long id) {

		PriorityDto priorityDto = priorityService.getpriorityById(id);

		return priorityDto;
	}

}
