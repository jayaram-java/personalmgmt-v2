package com.company.Personalmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.Personalmgmt.dto.GeneralDto;
import com.company.Personalmgmt.service.RestService;

@Controller
public class RestController {
	
	
	@Autowired
	RestService restService;

	
	
	@RequestMapping(value = "/moneyconverter")
	@ResponseBody
	public GeneralDto moneyConverter(@RequestParam String currency) {

		System.out.println("currency = " + currency);

		GeneralDto generalDto = restService.callingMoneyConverter(currency);

		return generalDto;
	}

	@RequestMapping(value = "/initiate")
	@ResponseBody
	public GeneralDto initiate() {

		GeneralDto generalDto = restService.callingMoneyConverter("INR");

		return generalDto;
	}

	

}
