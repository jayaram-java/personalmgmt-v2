package com.company.Personalmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.Personalmgmt.dto.SecondaryUserDetailsDto;
import com.company.Personalmgmt.service.EmailService;

@Controller
public class EmailController {

	@Autowired
	EmailService emailService;

	@RequestMapping(value = "/getallemailaddress")
	@ResponseBody
	public List<SecondaryUserDetailsDto> getAllEmailAddress() {

		List<SecondaryUserDetailsDto> secondaryUserDetailsDtos = emailService.getAllEmailAddress();

		return secondaryUserDetailsDtos;
	}

	@RequestMapping(value = "/emailserviceforexpense")
	@ResponseBody
	public boolean sendEmailForExpense() {

		boolean response = emailService.sendEmailForExpense();

		return response;
	}

	@RequestMapping(value = "/emailserviceforchecklist")
	@ResponseBody
	public boolean sendEmailForCheckList() {

		boolean response = emailService.sendEmailForCheckList();

		return response;
	}
	
	@RequestMapping(value = "/emailserviceforclient")
	@ResponseBody
	public boolean sendEmailForClientCommunication(String toAddress) {

		boolean response = emailService.sendEmailtoClients(toAddress);

		return response;
	}
	
	
	
	
	
	
	

}
