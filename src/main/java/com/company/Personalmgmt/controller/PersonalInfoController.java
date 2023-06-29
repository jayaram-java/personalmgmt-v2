package com.company.Personalmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.Personalmgmt.dto.PersonalInfoDto;
import com.company.Personalmgmt.model.WorkingCompanyDetail;
import com.company.Personalmgmt.service.PersonalInfoService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
public class PersonalInfoController {

	@Autowired
	PersonalInfoService personalInfoService;

	@RequestMapping("/savepersonalinfo")
	@ResponseBody
	public JsonObject savePersonalInfo(@RequestBody PersonalInfoDto personalInfoDto) {

		JsonObject jsonObject = new JsonObject();

		boolean response = personalInfoService.savePersonalInfo(personalInfoDto);

		jsonObject.add("personal info result", new Gson().toJsonTree(response));

		return jsonObject;
	}

	@RequestMapping("/gsfetchallpersonalinfo")
	@ResponseBody
	public JsonObject gsFetchAllPersonalInfo() {

		JsonObject jsonObject = new JsonObject();

		List<PersonalInfoDto> personalInfoDto = personalInfoService.fetchAllPersonalInfo();

		jsonObject.add("personal info result", new Gson().toJsonTree(personalInfoDto));

		return jsonObject;
	}

	@RequestMapping("/fetchallpersonalinfo")
	@ResponseBody
	public List<PersonalInfoDto> fetchAllPersonalInfo() {

		List<PersonalInfoDto> personalInfoDto = personalInfoService.fetchAllPersonalInfo();

		return personalInfoDto;
	}
	
	@RequestMapping("/fetchPersonalInfo")
	@ResponseBody
	public List<PersonalInfoDto> fetchPersonalInfo() {

		List<PersonalInfoDto> personalInfoDto = personalInfoService.fetchPersonalInfo();

		return personalInfoDto;
	}
	
	
	
	@RequestMapping("/saveworkingcompanydetail")
	@ResponseBody
	public JsonObject saveWorkingCompanyDetail(@RequestBody WorkingCompanyDetail workingCompanyDetail) {

		JsonObject jsonObject = new JsonObject();

		boolean response = personalInfoService.saveWorkingCompanyDetail(workingCompanyDetail);

		jsonObject.add("personal info result", new Gson().toJsonTree(response));

		return jsonObject;
	}
	
	@RequestMapping("/fetchallworkingcompanydetail")
	@ResponseBody
	public List<WorkingCompanyDetail> fetchAllCompanyDetail() {

		List<WorkingCompanyDetail> workingCompanyDetails = personalInfoService.fetchAllWorkingCompanyDetail();

		return workingCompanyDetails;
	}

	
	
	
	

}
