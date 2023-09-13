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
import com.company.Personalmgmt.dto.BillerResponse;
import com.company.Personalmgmt.dto.MoveForwardDto;
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
	public BillerDTO testing() {
		String jsonResponse = "{\"requestId\":\"123\",\"responseStatus\":\"Success\",\"timestamp\":\"2023-09-13T12:50:44.406Z\",\"billers\":[{\"address\":{\"addressLine1\":null,\"city\":null,\"state\":null,\"zipCode\":null,\"country\":\"USA\"},\"BusinessDaysToDeliver\":\"null\",\"billerId\":\"40\",\"billerName\":\"ADVANTANATIONALBANKMORTGAGE\",\"nickName\":\"ADVANTANATIONALBANKMORTGAGE\",\"ebillSupport\":\"Q:Thisdataisuser-generated.Wheredoesthiscomefrom?\",\"accountNumber\":\"Q:Whatisthis?\",\"NextAvailablePaymentDeliveryDate\":\"Q:Howisthisdetermined?\",\"eBillStatus\":\"Q:Thisdataisuser-generated.Wheredoesthiscomefrom?\",\"paymentMethod\":null,\"status\":null}]}";

		ObjectMapper objectMapper = new ObjectMapper();

		BillerDTO response1 = null;
		try {
			response1 = objectMapper.readValue(jsonResponse, BillerDTO.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(response.getRequestId());

		ResponseEntity<BillerDTO> response = null;

		response = ResponseEntity.ok(response1);

		return response1;
	}

}
