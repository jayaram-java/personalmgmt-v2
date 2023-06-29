package com.company.Personalmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.Personalmgmt.model.NextCompany;
import com.company.Personalmgmt.service.NextCompanyService;

@Controller
@RequestMapping(value = "/nxtcompany")
public class NextCompanyController {

	@Autowired
	private NextCompanyService nextCompanyService;

	@RequestMapping(value = "/fetchalldetails")
	@ResponseBody
	public List<NextCompany> fetchAllNextCompanyDetails() {

		List<NextCompany> nextCompanies = nextCompanyService.getNextCompanyDetails();

		return nextCompanies;

	}

}
