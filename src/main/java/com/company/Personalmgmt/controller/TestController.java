package com.company.Personalmgmt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.Personalmgmt.dto.BillerDTO;
import com.company.Personalmgmt.dto.MoveForwardDto;
import com.company.Personalmgmt.dto.PayeeDetails;
import com.company.Personalmgmt.dto.ResponseDto;
import com.company.Personalmgmt.exception.InvalidDataException;
import com.company.Personalmgmt.repository.ExpenseCategoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class TestController {

	@Autowired
	ExpenseCategoryRepository expenseCategoryRepository;

	@Autowired
	ResponseDto responseDto;

	@RequestMapping(value = "/getTestDetails")
	@ResponseBody
	public List<MoveForwardDto> getMoveForwardDetails(@RequestParam String category) {

		System.out.println(category);

		if (category == null || category == "") {

			System.out.println(category);

			throw new InvalidDataException(responseDto.getStatusFalse(), responseDto.getStatusDesc());

		}

		List<MoveForwardDto> moveForwarddtos = new ArrayList<MoveForwardDto>();

		return moveForwarddtos;
	}

	@RequestMapping(value = "/testingv1")
	@ResponseBody
	public PayeeDetails testing() {
		String jsonResponse = "{\"requestId\":\"9000\",\"backwardFlag\":false,\"forwardFlag\":true,\"responseStatus\":\"Success\",\"timestamp\":\"2023-09-14T11:59:52.055Z\",\"httpStatusCode\":200,\"billers\":[{\"billerName\":\"1ST FINANCIAL BANK USA - CCARD\",\"billerCategory\":\"Pending\",\"billerId\":\"548608\"},{\"billerName\":\"1ST FLORIDA INTEGRITY BANK LOAN\",\"billerCategory\":\"Pending\",\"billerId\":\"586348\"},{\"billerName\":\"1ST NATIONAL BANK OH\",\"billerCategory\":\"Pending\",\"billerId\":\"592306\"},{\"billerName\":\"1ST NATIONAL BANK ST IGNACE LOAN\",\"billerCategory\":\"Pending\",\"billerId\":\"580734\"},{\"billerName\":\"1ST SOURCE BANK LOAN\",\"billerCategory\":\"Pending\",\"billerId\":\"585141\"},{\"billerName\":\"ACNB BANK LOAN\",\"billerCategory\":\"Pending\",\"billerId\":\"570931\"},{\"billerName\":\"ADVANTA NATIONAL BANK MORTGAGE\",\"billerCategory\":\"Pending\",\"billerId\":\"86591\"},{\"billerName\":\"AGFIRST FARM CREDIT BANK\",\"billerCategory\":\"Pending\",\"billerId\":\"576207\"},{\"billerName\":\"AIG FEDERAL SAVINGS BANK\",\"billerCategory\":\"Pending\",\"billerId\":\"501735\"},{\"billerName\":\"ALLEGIANCE BANK TEXAS LOAN\",\"billerCategory\":\"Pending\",\"billerId\":\"583093\"}]}";

		ObjectMapper objectMapper = new ObjectMapper();

		PayeeDetails response1 = null;
		try {
			response1 = objectMapper.readValue(jsonResponse, PayeeDetails.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(response.getRequestId());

		ResponseEntity<PayeeDetails> response = null;

		response = ResponseEntity.ok(response1);

		return response1;
	}

}
